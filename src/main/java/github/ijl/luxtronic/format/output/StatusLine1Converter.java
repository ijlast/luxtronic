package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

@Service
public class StatusLine1Converter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 0, "HP is running" }, { 1, "HP is off" },
			{ 2, "HP is coming" }, { 3, "Error: mem space 0" }, { 4, "Defrost" }, { 5, "Waiting for LIN" },
			{ 6, "Comp. heats up" }, { 7, "Pump flow" }, };

	public StatusLine1Converter() {
		super(HUMAN_READABLE_VALUES);
	}

}
