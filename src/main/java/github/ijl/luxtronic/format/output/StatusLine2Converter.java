package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

@Service
public class StatusLine2Converter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 0, "since" }, { 1, "in" }, };

	public StatusLine2Converter() {
		super(HUMAN_READABLE_VALUES);
	}
}
