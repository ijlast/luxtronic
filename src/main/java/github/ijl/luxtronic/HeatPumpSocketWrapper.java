package github.ijl.luxtronic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import github.ijl.luxtronic.config.ServiceProperties;

/**
 * This is a wrapper around a socket to the heat pump. Ideally it should be
 * pooled.
 */
@Service
@Slf4j
public final class HeatPumpSocketWrapper implements AutoCloseable {
    public static final int BYTES_PER_INT = 4;

    private final ServiceProperties properties;
    private Socket socket;
    private volatile int failCount = 0;

    @Autowired
    public HeatPumpSocketWrapper(final ServiceProperties properties) {
        this.properties = properties;
    }

    /**
     * Opens a socket based on the configuration
     *
     * @return Socket, connected to the heatpump
     * @throws UnknownHostException
     * @throws IOException
     */
    @PostConstruct
    protected void connect() throws IOException {
        if (socket == null || !socket.isConnected()) {
            final InetAddress address = InetAddress.getByName(properties.getIp());
            log.info("Opening heat pump connection...");
            log.info("Using IP Address: " + properties.getIp());
            log.info("Using Port: " + properties.getPort());
            socket = new Socket(address.getHostAddress(), Integer.parseInt(properties.getPort()));
            socket.setKeepAlive(false);
        }
    }

    /**
     * Closes the socket.
     *
     * @param pSocket
     * @throws IOException
     */
    @PreDestroy
    @Override
    public void close() throws Exception {
        log.info("Closing heat pump connection!");
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }

    /**
     * Reads data back variable amounts of data via the socket
     *
     * @param pContainsStatus
     *            set to true if reading back after 3004 (Read calculations)
     *            otherwise leave it as false.
     * @return ByteBuffer contain the data returned by the read.
     * @throws IOException
     */
    public ByteBuffer read(final boolean pContainsStatus) throws IOException {
        connect();
        // First read back the command that was issued.
        final InputStream is = socket.getInputStream();
        final ByteBuffer buffer = createBigEndianByteBuffer(BYTES_PER_INT);
        is.read(buffer.array());
        log.debug("Read command value: " + buffer.getInt());

        // Now read back the status value if necessary e.g. 3004 command.
        if (pContainsStatus) {
            buffer.clear();
            is.read(buffer.array());
            log.debug("Read status value: " + buffer.getInt());
        }

        // Now read back the amount of integer data that is being returned by the
        // heat pump
        buffer.clear();
        is.read(buffer.array());
        int remaining = buffer.getInt();

        // Finally, read back all the data (integers, not bytes!)
        int read = 0;
        final int size = remaining * BYTES_PER_INT;
        final ByteBuffer data = createBigEndianByteBuffer(size);
        while (read < size) {
            read += is.read(data.array(), read, size - read);
            log.debug("Bytes read: " + read + " / " + size);
        }
        return data;
    }

    /**
     * Reads back fixed amounts of data via the socket. When command 3002
     * (setParameter) is sent to the heat pump it should respond with 1) command (4
     * bytes) 2) the parameter value (4 bytes)
     *
     * @param pData
     * @return
     * @throws IOException
     */
    public ByteBuffer read(final int pCount) throws IOException {
        log.debug("read: reading " + pCount + "bytes of data ");
        connect();
        final ByteBuffer readBuffer = createBigEndianByteBuffer(BYTES_PER_INT * pCount);
        // Read the result
        final InputStream is = socket.getInputStream();
        is.read(readBuffer.array());
        dump(readBuffer);
        return readBuffer;
    }

    /**
     * Writes to the heat pump, a command and variable amounts of data
     *
     * @param pCommand
     *            the command, must be present.
     * @param pData
     *            variables amounts of data.
     * @return
     * @throws IOException
     */
    public ByteBuffer write(final int pCommand, final int... pData) throws Exception {
        try {
            connect();
            // Create the buffer with at least 4 bytes.
            final ByteBuffer writeBuffer = createBigEndianByteBuffer(BYTES_PER_INT * (1 + pData.length));

            // Write the command
            log.debug("write: sending values to heatpump: ");
            writeBuffer.putInt(pCommand);
            log.debug("write: " + pCommand);
            // Write the data, if any.
            for (int i = 0; i < pData.length; i++) {
                writeBuffer.putInt(pData[i]);
                log.debug("write: " + pData[i]);
            }

            // Send the data.
            writeBuffer.flip();
            final OutputStream os = socket.getOutputStream();
            os.write(writeBuffer.array());
            os.flush();
            failCount = 0;
            return writeBuffer;
        } catch (final SocketException _) {
            if (failCount++ < 3) {
                close();
                return write(pCommand, pData);
            } else {
                System.exit(1); // should trigger restart or docker container
                return null;
            }
        }
    }

    public void dump(final ByteBuffer pBuffer) {
        for (int i = pBuffer.position(); i < pBuffer.limit(); i++) {
            byte value = pBuffer.get();
            log.debug(i + ", " + Integer.toHexString(Byte.toUnsignedInt(value)));
        }
    }

    public void dumpInt(final ByteBuffer pBuffer) {
        for (int i = pBuffer.position(); i < pBuffer.limit(); i += BYTES_PER_INT) {
            int value = pBuffer.getInt();
            log.debug(i + " ,\t " + value + ",\t " + ((char) value));
        }
    }

    /**
     * Convenience method for big endian byte buffer.
     *
     * @param pBytes
     * @return
     */
    private ByteBuffer createBigEndianByteBuffer(final int pBytes) {
        final ByteBuffer buffer = ByteBuffer.allocate(pBytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer;
    }
}
