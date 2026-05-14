package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

@Service
public class OperatingConditionConverter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 0, "0: heating" }, { 1, "1: hot water" },
			{ 2, "2: swimming pool / photovoltaic" }, { 3, "3: power supply" }, { 4, "4: defrosting" },
			{ 5, "5: no request" }, { 6, "6: heating ext. Energy source" }, { 7, "7: cooling mode" }, };

	public OperatingConditionConverter() {
		super(HUMAN_READABLE_VALUES);
	}
}
