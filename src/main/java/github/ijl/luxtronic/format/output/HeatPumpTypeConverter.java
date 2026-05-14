package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

@Service
public class HeatPumpTypeConverter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 0, "ERC" }, { 1, "SW1" }, { 2, "SW2" },
			{ 3, "WW1" }, { 4, "WW2" }, { 5, "L1I" }, { 6, "L2I" }, { 7, "L1A" }, { 8, "L2A" }, { 9, "KSW" },
			{ 10, "KLW" }, { 11, "SWC" }, { 12, "LWC" }, { 13, "L2G" }, { 14, "TC" }, { 15, "L1I407" },
			{ 16, "L2I407" }, { 17, "L1A407" }, { 18, "L2A407" }, { 19, "L2G407" }, { 20, "LWC407" }, { 21, "L1AREV" },
			{ 22, "L2AREV" }, { 23, "WWC1" }, { 24, "WWC2" }, { 25, "L2G404" }, { 26, "WZW" }, { 27, "L1S" },
			{ 28, "L1H" }, { 29, "L2H" }, { 30, "WZWD" }, { 31, "ERC" }, { 40, "WWB_20" }, { 41, "LD5" }, { 42, "LD7" },
			{ 43, "SW 37_45" }, { 44, "SW 58_69" }, { 45, "SW 29_56" }, { 46, "LD5 (230V)" }, { 47, "LD7 (230V)" },
			{ 48, "LD9" }, { 49, "LD5 REV" }, { 50, "LD7 REV" }, { 51, "LD5 REV 230V" }, { 52, "LD7 REV 230V" },
			{ 53, "LD9 REV 230V" }, { 54, "SW 291" }, { 55, "LW SEC" }, { 56, "HMD 2" }, { 57, "MSW 4" },
			{ 58, "MSW 6" }, { 59, "MSW 8" }, { 60, "MSW 10" }, { 61, "MSW 12" }, { 62, "MSW 14" }, { 63, "MSW 17" },
			{ 64, "MSW 19" }, { 65, "MSW 23" }, { 66, "MSW 26" }, { 67, "MSW 30" }, { 68, "MSW 4S" }, { 69, "MSW 6S" },
			{ 70, "MSW 8S" }, { 71, "MSW 10S" }, { 72, "MSW 13S" }, { 73, "MSW 16S" }, { 74, "MSW2-6S" },
			{ 75, "MSW4-16" }, };

	public HeatPumpTypeConverter() {
		super(HUMAN_READABLE_VALUES);
	}
}
