package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

@Service
public class StatusLine3Converter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 0, "heating mode" }, { 1, "no request" },
			{ 2, "mains switch-on delay" }, { 3, "switching cycle lock" }, { 4, "blocking time" },
			{ 5, "dom. hot water" }, { 6, "info heating program" }, { 7, "defrost" }, { 8, "pump flow" },
			{ 9, "thermal disinfection" }, { 10, "cooling mode" }, { 12, "swimming pool / photovoltaic " },
			{ 13, "heating ext. Energy source" }, { 14, "hot water ext. Energy source" }, { 16, "flow monitoring" },
			{ 17, "second heat generator 1 operation " }, };

	public StatusLine3Converter() {
		super(HUMAN_READABLE_VALUES);
	}
}
