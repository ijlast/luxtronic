package github.ijl.luxtronic.config.v161;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.format.output.AsciiConverter;
import github.ijl.luxtronic.format.output.BooleanConverter;
import github.ijl.luxtronic.format.output.HeatPumpTypeConverter;
import github.ijl.luxtronic.format.output.IPConverter;
import github.ijl.luxtronic.format.output.OperatingConditionConverter;
import github.ijl.luxtronic.format.output.SecondsConverter;
import github.ijl.luxtronic.format.output.SecondsToHoursConverter;
import github.ijl.luxtronic.format.output.ShutdownCodeConverter;
import github.ijl.luxtronic.format.output.StatusLine1Converter;
import github.ijl.luxtronic.format.output.StatusLine2Converter;
import github.ijl.luxtronic.format.output.StatusLine3Converter;
import github.ijl.luxtronic.format.output.TemperatureConverter;
import github.ijl.luxtronic.format.output.TimestampConverter;

public enum Calculations {
	ID_WEB_Temperatur_TVL(10, "Flow temperature heating circuit.", TemperatureConverter.class),
	ID_WEB_Temperatur_TRL(11, "Return temperature heating circuit.", TemperatureConverter.class),
	ID_WEB_Sollwert_TRL_HZ(12, "Return setpoint heating circuit.", TemperatureConverter.class),
	ID_WEB_Temperatur_TRL_ext(13, "Return temperature in the separation tank.", TemperatureConverter.class),
	ID_WEB_Temperatur_THG(14, "Hot gas temperature.", TemperatureConverter.class),
	ID_WEB_Temperatur_TA(15, "Outside temperature.", TemperatureConverter.class),
	ID_WEB_Mitteltemperatur(16, "Average temperature outside over 24 h (function heating limit).",
			TemperatureConverter.class),
	ID_WEB_Temperatur_TBW(17, "Hot water actual temperature.", TemperatureConverter.class),
	ID_WEB_Einst_BWS_akt(18, "Hot water set temperature.", TemperatureConverter.class),
	ID_WEB_Temperatur_TWE(19, "Brine inlet temperature", TemperatureConverter.class),
	ID_WEB_Temperatur_TWA(20, "Heat sources outlet temperature", TemperatureConverter.class),
	ID_WEB_Temperatur_TFB1(21, "Mixing circuit 1 flow temperature", TemperatureConverter.class),
	ID_WEB_Sollwert_TVL_MK1(22, "Mixing circuit 1 flow setpoint temperature", TemperatureConverter.class),
	ID_WEB_Temperatur_RFV(23, "Room temperature room station 1", TemperatureConverter.class),
	ID_WEB_Temperatur_TFB2(24, "Mixing circuit 2 flow temperature", TemperatureConverter.class),
	ID_WEB_Sollwert_TVL_MK2(25, "Mixing circuit 2 flow setpoint temperature", TemperatureConverter.class),
	ID_WEB_Temperatur_TSK(26, "Feeler solar collector", TemperatureConverter.class),
	ID_WEB_Temperatur_TSS(27, "Feeler solar storage tank", TemperatureConverter.class),
	ID_WEB_Temperatur_TEE(28, "Sensor external energy source", TemperatureConverter.class),
	ID_WEB_ASDin(29, "Input defrost end, brine pressure, flow", BooleanConverter.class),
	ID_WEB_BWTin(30, "Input Domestic hot water thermostat", BooleanConverter.class),
	ID_WEB_EVUin(31, "Entrance EVU lock", BooleanConverter.class),
	ID_WEB_HDin(32, "Input High pressure circuit", BooleanConverter.class),
	ID_WEB_MOTin(33, "Input Motor protection OK", BooleanConverter.class),
	ID_WEB_NDin(34, "Input low pressure", BooleanConverter.class),
	ID_WEB_PEXin(35, "Input monitoring contact for potentiostat", BooleanConverter.class),
	ID_WEB_SWTin(36, "Entrance swimming-pool thermostat", BooleanConverter.class),
	ID_WEB_AVout(37, "Output defrost valve", BooleanConverter.class),
	ID_WEB_BUPout(38, "Output dhw pump / diverter valve", BooleanConverter.class),
	ID_WEB_HUPout(39, "Output Heating circulation pump", BooleanConverter.class),
	ID_WEB_MA1out(40, "Output Mixing circle 1 on", BooleanConverter.class),
	ID_WEB_MZ1out(41, "Output mixing circuit 1 to", BooleanConverter.class),
	ID_WEB_VENout(42, "Output Ventilation", BooleanConverter.class),
	ID_WEB_VBOout(43, "Output brine pump / fan", BooleanConverter.class),
	ID_WEB_VD1out(44, "Output Compressor 1", BooleanConverter.class),
	ID_WEB_VD2out(45, "Output Compressor 2", BooleanConverter.class),
	ID_WEB_ZIPout(46, "Output circulation pump", BooleanConverter.class),
	ID_WEB_ZUPout(47, "Output additional circulation pump", BooleanConverter.class),
	ID_WEB_ZW1out(48, "Output control signal additional heating v. Heating", BooleanConverter.class),
	ID_WEB_ZW2SSTout(49, "Output control signal additional heating / interference signal", BooleanConverter.class),
	ID_WEB_ZW3SSTout(50, "Output additional heater 3", BooleanConverter.class),
	ID_WEB_FP2out(51, "Output Pump mixing circuit 2", BooleanConverter.class),
	ID_WEB_SLPout(52, "Output solar charging pump", BooleanConverter.class),
	ID_WEB_SUPout(53, "Output swimming pool pump", BooleanConverter.class),
	ID_WEB_MZ2out(54, "Output mixing circuit 2 to", BooleanConverter.class),
	ID_WEB_MA2out(55, "Output Mixing circuit 2 on", BooleanConverter.class),
	ID_WEB_Zaehler_BetrZeitVD1(56, "Operating hours compressor 1", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitImpVD1(57, "Impulse compressor 1", OneToOneConverter.class),
	ID_WEB_Zaehler_BetrZeitVD2(58, "Operating hours compressor 2", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitImpVD2(59, "Impulse compressor 2", OneToOneConverter.class),
	ID_WEB_Zaehler_BetrZeitZWE1(60, "Operating hours Second heat generator 1", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitZWE2(61, "Operating hours Second heat generator 2", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitZWE3(62, "Operating hours Second heat generator 3", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitWP(63, "Operating hours heat pump", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitHz(64, "Hours of operation heating", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitBW(65, "Operating hours hot water", SecondsToHoursConverter.class),
	ID_WEB_Zaehler_BetrZeitKue(66, "Operating hours cooling", SecondsToHoursConverter.class),
	ID_WEB_Time_WPein_akt(67, "Heat pump has been running", SecondsConverter.class),
	ID_WEB_Time_ZWE1_akt(68, "Second heat generator 1 is running since", SecondsConverter.class),
	ID_WEB_Time_ZWE2_akt(69, "Second heat generator 2 is running since", SecondsConverter.class),
	ID_WEB_Timer_EinschVerz(70, "Netzeinschaltverzogerung", SecondsConverter.class),
	ID_WEB_Time_SSPAUS_akt(71, "Interlocking Off", SecondsConverter.class),
	ID_WEB_Time_SSPEIN_akt(72, "Interlocking lock on", SecondsConverter.class),
	ID_WEB_Time_VDStd_akt(73, "Compressor life", SecondsConverter.class),
	ID_WEB_Time_HRM_akt(74, "Heating controller multi-time", SecondsConverter.class),
	ID_WEB_Time_HRW_akt(75, "Heating controller less-time", SecondsConverter.class),
	ID_WEB_Time_LGS_akt(76, "Thermal disinfection has been going on", SecondsConverter.class),
	ID_WEB_Time_SBW_akt(77, "Lock hot water", SecondsConverter.class),
	ID_WEB_Code_WP_akt(78, "Heat pump type", HeatPumpTypeConverter.class),
	ID_WEB_BIV_Stufe_akt(79, "Bivalence", OneToOneConverter.class),
	ID_WEB_WP_BZ_akt(80, "operating condition", OperatingConditionConverter.class),
	ID_WEB_SoftStand1(81, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand2(82, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand3(83, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand4(84, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand5(85, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand6(86, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand7(87, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand8(88, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand9(89, "software Version", AsciiConverter.class),
	ID_WEB_SoftStand10(90, "software Version", AsciiConverter.class),
	ID_WEB_AdresseIP_akt(91, "IP address", IPConverter.class),
	ID_WEB_SubNetMask_akt(92, "subnet Mask", IPConverter.class),
	ID_WEB_Add_Broadcast(93, "Broadcast address", IPConverter.class),
	ID_WEB_Add_StdGateway(94, "Standard gateway", IPConverter.class),
	ID_WEB_ERROR_Time0(95, "Timestamp Error 0 in memory", TimestampConverter.class),
	ID_WEB_ERROR_Time1(96, "Timestamp Error 1 in memory", TimestampConverter.class),
	ID_WEB_ERROR_Time2(97, "Timestamp Error 2 in memory", TimestampConverter.class),
	ID_WEB_ERROR_Time3(98, "Timestamp Error 3 in memory", TimestampConverter.class),
	ID_WEB_ERROR_Time4(99, "Timestamp Error 4 in memory", TimestampConverter.class),
	ID_WEB_ERROR_Nr0(100, "Error code Error 0 in memory", OneToOneConverter.class),
	ID_WEB_ERROR_Nr1(101, "Error code Error 1 in memory", OneToOneConverter.class),
	ID_WEB_ERROR_Nr2(102, "Error code Error 2 in memory", OneToOneConverter.class),
	ID_WEB_ERROR_Nr3(103, "Error code Error 3 in memory", OneToOneConverter.class),
	ID_WEB_ERROR_Nr4(104, "Error code Error 4 in memory", OneToOneConverter.class),
	ID_WEB_AnzahlFehlerInSpeicher(105, "Number of errors in memory", OneToOneConverter.class),
	ID_WEB_Switchoff_file_Nr0(106, "Basic shutdown 0 in memory", ShutdownCodeConverter.class),
	ID_WEB_Switchoff_file_Nr1(107, "Basic shutdown 1 in memory", ShutdownCodeConverter.class),
	ID_WEB_Switchoff_file_Nr2(108, "Basic shutdown 2 in memory", ShutdownCodeConverter.class),
	ID_WEB_Switchoff_file_Nr3(109, "Basic shutdown 3 in the store", ShutdownCodeConverter.class),
	ID_WEB_Switchoff_file_Nr4(110, "Basic shutdown 4 in memory", ShutdownCodeConverter.class),
	ID_WEB_Switchoff_file_Time0(111, "Timestamp Shutdown 0 in memory", TimestampConverter.class),
	ID_WEB_Switchoff_file_Time1(112, "Timestamp Shutdown 1 in memory", TimestampConverter.class),
	ID_WEB_Switchoff_file_Time2(113, "Timestamp Shutdown 2 in memory", TimestampConverter.class),
	ID_WEB_Switchoff_file_Time3(114, "Timestamp Shutdown 3 in memory", TimestampConverter.class),
	ID_WEB_Switchoff_file_Time4(115, "Timestamp Shutdown 4 in memory", TimestampConverter.class),
	ID_WEB_Comfort_exists(116, "Comfort board installed", BooleanConverter.class),
	ID_WEB_HauptMenuStatus_Zeile1(117, "Status line 1", StatusLine1Converter.class),
	ID_WEB_HauptMenuStatus_Zeile2(118, "Status line 2", StatusLine2Converter.class),
	ID_WEB_HauptMenuStatus_Zeile3(119, "Status line 3", StatusLine3Converter.class),
	ID_WEB_HauptMenuStatus_Zeit(120, "Time since / in (in combination with # 118)", SecondsConverter.class),
	ID_WEB_HauptMenuAHP_Stufe(121, "Stage heating program", OneToOneConverter.class),
	ID_WEB_HauptMenuAHP_Temp(122, "Temperature heating program", OneToOneConverter.class),
	ID_WEB_HauptMenuAHP_Zeit(123, "Runtime heating program", SecondsConverter.class),
	ID_WEB_SH_BWW(124, "Domestic hot water active / inactive symbol", BooleanConverter.class),
	ID_WEB_SH_HZ(125, "Heating icon", OneToOneConverter.class),
	ID_WEB_SH_MK1(126, "Mixing circle 1 symbol", OneToOneConverter.class),
	ID_WEB_SH_MK2(127, "Mixing circle 2 symbol", OneToOneConverter.class),
	ID_WEB_Einst_Kurzrpgramm(128, "Setting short program", OneToOneConverter.class),
	ID_WEB_StatusSlave_1(129, "Status slave 1", OneToOneConverter.class),
	ID_WEB_StatusSlave_2(130, "Status slave 2", OneToOneConverter.class),
	ID_WEB_StatusSlave_3(131, "Status slave 3", OneToOneConverter.class),
	ID_WEB_StatusSlave_4(132, "Status slave 4", OneToOneConverter.class),
	ID_WEB_StatusSlave_5(133, "Status slave 5", OneToOneConverter.class),
	ID_WEB_AktuelleTimeStamp(134, "Current time of the heat pump", OneToOneConverter.class),
	ID_WEB_SH_MK3(135, "Mixing circle 3 symbol", OneToOneConverter.class),
	ID_WEB_Sollwert_TVL_MK3(136, "Mixing circuit 3 flow setpoint temperature", OneToOneConverter.class),
	ID_WEB_Temperatur_TFB3(137, "Mixing circuit 3 flow temperature", OneToOneConverter.class),
	ID_WEB_MZ3out(138, "Output Mixing circuit 3 To", BooleanConverter.class),
	ID_WEB_MA3out(139, "Output Mixing circuit 3 on", BooleanConverter.class),
	ID_WEB_FP3out(140, "Pump mixing circuit 3", BooleanConverter.class),
	ID_WEB_Time_AbtIn(141, "Time to defrost", SecondsConverter.class),
	ID_WEB_Temperatur_RFV2(142, "Room temperature room station 2", OneToOneConverter.class),
	ID_WEB_Temperatur_RFV3(143, "Room temperature room station 3", OneToOneConverter.class),
	ID_WEB_SH_SW(144, "Timer swimming pool icon", OneToOneConverter.class),
	ID_WEB_Zaehler_BetrZeitSW(145, "Operating hours swimming pool", OneToOneConverter.class),
	ID_WEB_FreigabKuehl(146, "Release cooling", BooleanConverter.class),
	ID_WEB_AnalogIn(147, "Analog input signal", OneToOneConverter.class),
	ID_WEB_SonderZeichen(148, "??", OneToOneConverter.class),
	ID_WEB_SH_ZIP(149, "Circulation pumps icon", OneToOneConverter.class),
	ID_WEB_WebsrvProgrammWerteBeobarten(150, "??", OneToOneConverter.class),
	ID_WEB_WMZ_Heizung(151, "Heat meter heater", OneToOneConverter.class),
	ID_WEB_WMZ_Brauchwasser(152, "Heat meter for process water", OneToOneConverter.class),
	ID_WEB_WMZ_Schwimmbad(153, "Heat meter swimming pool", OneToOneConverter.class),
	ID_WEB_WMZ_Seit(154, "Heat meter total", OneToOneConverter.class),
	ID_WEB_WMZ_Durchfluss(155, "Heat meter flow", OneToOneConverter.class),
	ID_WEB_AnalogOut1(156, "Analog output 1", OneToOneConverter.class),
	ID_WEB_AnalogOut2(157, "Analog output 2", OneToOneConverter.class),
	ID_WEB_Time_Heissgas(158, "Lock second compressor hot gas", SecondsConverter.class),
	ID_WEB_Temp_Lueftung_Zuluft(159, "supply air temperature", OneToOneConverter.class),
	ID_WEB_Temp_Lueftung_Abluft(160, "exhaust air temperature", OneToOneConverter.class),
	ID_WEB_Zaehler_BetrZeitSolar(161, "Operating hours counter Solar", SecondsConverter.class),
	ID_WEB_AnalogOut3(162, "Analog output 3", OneToOneConverter.class),
	ID_WEB_AnalogOut4(163, "Analog output 4", OneToOneConverter.class),
	ID_WEB_Out_VZU(164, "Supply air fan (defrost function)", OneToOneConverter.class),
	ID_WEB_Out_VAB(165, "Exhaust fan", OneToOneConverter.class),
	ID_WEB_Out_VSK(166, "Output VSK", BooleanConverter.class),
	ID_WEB_Out_FRH(167, "Output FRH", BooleanConverter.class),
	ID_WEB_AnalogIn2(168, "Analog input 2", OneToOneConverter.class),
	ID_WEB_AnalogIn3(169, "Analog input 3", OneToOneConverter.class),
	ID_WEB_SAXin(170, "Input SAX", BooleanConverter.class), ID_WEB_SPLin(171, "Input SPL", BooleanConverter.class),
	ID_WEB_Compact_exists(172, "Ventilation board installed", BooleanConverter.class),
	ID_WEB_Durchfluss_WQ(173, "Flow heat source", OneToOneConverter.class),
	ID_WEB_LIN_exists(174, "LIN BUS installed", BooleanConverter.class),
	ID_WEB_LIN_ANSAUG_VERDAMPFER(175, "Temperature intake evaporator", OneToOneConverter.class),
	ID_WEB_LIN_ANSAUG_VERDICHTER(176, "Temperature intake compressor", OneToOneConverter.class),
	ID_WEB_LIN_VDH(177, "Temperature compressor heating", OneToOneConverter.class),
	ID_WEB_LIN_UH(178, "overheating", OneToOneConverter.class),
	ID_WEB_LIN_UH_Soll(179, "Overheating target", OneToOneConverter.class),
	ID_WEB_LIN_HD(180, "high pressure", OneToOneConverter.class),
	ID_WEB_LIN_ND(181, "low pressure", OneToOneConverter.class),
	ID_WEB_LIN_VDH_out(182, "Output compressor heater", BooleanConverter.class);

	private final Integer mIndex;
	private final String mDescription;
	private final Class<? extends FormatConverter> mFormatConverterClass;

	private Calculations(final Integer pIndex, final String pDescription,
			final Class<? extends FormatConverter> pConverterClass) {
		mIndex = pIndex;
		mDescription = pDescription;
		mFormatConverterClass = pConverterClass;
	}

	public Integer getIntegerValue() {
		return mIndex;
	}

	public String getDescription() {
		return mDescription;
	}

	public Class<? extends FormatConverter> getFormatConverterClass() {
		return mFormatConverterClass;
	}

	public static Calculations getCalculation(final int pIndex) {
		Calculations calc = null;
		for (final Calculations testCalc : Calculations.class.getEnumConstants()) {
			if (pIndex == testCalc.getIntegerValue()) {
				calc = testCalc;
				break;
			}
		}
		return calc;
	}
}
