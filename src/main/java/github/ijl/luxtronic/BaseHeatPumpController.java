package github.ijl.luxtronic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import github.ijl.luxtronic.config.ServiceProperties;

public class BaseHeatPumpController implements AutoCloseable {
	private Log mLog = LogFactory.getLog(BaseHeatPumpController.class);

	@Autowired
	private ServiceProperties mProperties;
	private Socket mSocket;

	public BaseHeatPumpController() {
	}

	@PostConstruct
	public void connect() throws UnknownHostException, IOException {
		if (mSocket == null || !mSocket.isConnected()) {
			final InetAddress address = InetAddress.getByName(mProperties.getIp());
			mLog.info("Opening heat pump connection...");
			mLog.info("Using IP Address: " + mProperties.getIp());
			mLog.info("Using Port: " + mProperties.getPort());
			mSocket = new Socket(address.getHostAddress(), Integer.valueOf(mProperties.getPort()));
			mSocket.setKeepAlive(true);
		}
	}

	/**
	 * @see java.lang.AutoCloseable#close()
	 */
	@PreDestroy
	public void close() throws IOException {
		mLog.info("Closing heat pump connection!");
		if (mSocket != null) {
			mSocket.close();
			mSocket = null;
		}
	}

	public ByteBuffer read(final boolean pContainsStatus, final int pSize) throws IOException {
		try (final InputStream os = mSocket.getInputStream()) {
			final ByteBuffer buffer = ByteBuffer.allocate(4);
			buffer.order(ByteOrder.BIG_ENDIAN);
			os.read(buffer.array());
			mLog.debug("Read command value: " + buffer.getInt());
			if (pContainsStatus) {
				buffer.clear();
				os.read(buffer.array());
				mLog.debug("Read status value: " + buffer.getInt());
			}
			buffer.clear();
			os.read(buffer.array());
			int remaining = buffer.getInt();
			mLog.debug("Integer Data Remaining: " + remaining);
			final ByteBuffer data = ByteBuffer.allocate(remaining * pSize);
			final int read = os.read(data.array());
			data.limit(read);
			return data;
		}
	}

	public void dump(final ByteBuffer pBuffer) {
		for (int i = pBuffer.position(); i < pBuffer.limit(); i++) {
			byte value = pBuffer.get();
			mLog.info(i + ", " + Integer.toHexString(Byte.toUnsignedInt(value)));
		}
	}

	public void dumpInt(final ByteBuffer pBuffer) {
		for (int i = pBuffer.position(); i < pBuffer.limit(); i += 4) {
			int value = pBuffer.getInt();
			System.out.println(value);
		}
	}

	public ByteBuffer write(final int pCommand, final int... pData) throws IOException {
		final ByteBuffer writeBuffer = ByteBuffer.allocate(4 * (1 + pData.length));

		// Prepare the data to send
		writeBuffer.order(ByteOrder.BIG_ENDIAN);
		writeBuffer.putInt(pCommand);
		for (int i = 0; i < pData.length; i++) {
			writeBuffer.putInt(pData[i]);
		}
		writeBuffer.flip();

		// Send the data.
		final OutputStream os = mSocket.getOutputStream();
		os.write(writeBuffer.array());
		os.flush();
		writeBuffer.rewind();
		dump(writeBuffer);
		return writeBuffer;
	}

	/**
	 * When command 3002 (setParameter) is sent to the heat pump it should respond
	 * with 1) command (4 bytes) 2) the parameter value (4 bytes)
	 * 
	 * @param pData
	 * @return
	 * @throws IOException
	 */
	public ByteBuffer read3002(final int... pData) throws IOException {
		final ByteBuffer readBuffer = ByteBuffer.allocate(8);
		// Read the result
		final InputStream is = mSocket.getInputStream();
		is.read(readBuffer.array());
		dump(readBuffer);
		return readBuffer;
	}

	protected List<Integer> byteBufferToArray(final ByteBuffer pBuffer) {
		final List<Integer> dataArray = new ArrayList<Integer>();
		pBuffer.rewind();

		for (int i = pBuffer.position(); i < pBuffer.limit(); i += 4) {
			dataArray.add(pBuffer.getInt());
		}

		return dataArray;

	}
}
