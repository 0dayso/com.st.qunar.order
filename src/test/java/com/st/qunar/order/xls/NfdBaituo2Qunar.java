package com.st.qunar.order.xls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;

public class NfdBaituo2Qunar {

	private final static String baituoFileName = "/Users/kxhu/Documents/trip/百拓/北京出港NFD运价5月4日早.xlsx";
	private final static String qunarFileName = "/Users/kxhu/Documents/trip/百拓/北京出港NFD运价5月4日早_qunar2.xls";

	private final static String[] qunarheads = { "航空公司", "政策代码", "起飞城市", "到达城市", "航班限制", "航班号", "班期限制", "适用舱位",
			"票面价类型", "" + "票面价/折扣", "返点", "留钱", "销售起始日期", "销售结束日期", "旅行起始日期", "旅行结束日期", "航班起飞时间", "最晚提前出票时限",
			"最早提前出票时限", "" + "退改签说明", "舱位说明", "是否自动出票", "搭桥office号", "是否提供行程单", "退票规则", "改期规则", "是否允许签转", "是否提供常旅客积分",
			"" + "证件类型", "最大购买年龄", "最小购买年龄" };

	public static void main(String[] args) {
		// writeExcel();
		List<BaituoNfdData> baituofds = readBaituExcel();
		writeExcel(baituofds);
		System.out.println("write ok");
	}

	public static void writeExcel(List<BaituoNfdData> baitunfds) {
		Workbook wb = new HSSFWorkbook();
		try {
			FileOutputStream fileOut = new FileOutputStream(qunarFileName);
			Sheet sheet = wb.createSheet("btnfd");
			Row row = sheet.createRow(0);
			for (int i = 0; i < qunarheads.length; i++) {
				row.createCell(i).setCellValue(qunarheads[i]);
			}

			int r = 1;
			for (BaituoNfdData baituonfd : baitunfds) {
				row = sheet.createRow(r);
				row.createCell(0).setCellValue(baituonfd.getArilines());
				row.createCell(1).setCellValue("");
				row.createCell(2).setCellValue(baituonfd.getDep());
				row.createCell(3).setCellValue(baituonfd.getArr());
				if (r == 368) {
					System.out.println(r);
					System.out.println("dd");
				}
				if (!baituonfd.getUnlimitFlight().isEmpty()) {// 适合航班不为空
					row.createCell(4).setCellValue("适用");
					String str = flightExchange(baituonfd.getUnlimitFlight(), baituonfd.getArilines());
					row.createCell(5).setCellValue(str);
				} else if (!baituonfd.getLimitFlight().isEmpty()) {// 不适用航班不为空
					row.createCell(4).setCellValue("不适用");
					String str = flightExchange(baituonfd.getLimitFlight(), baituonfd.getArilines());
					row.createCell(5).setCellValue(str);
				}
				if (baituonfd.getUnlimitFlight().isEmpty() && baituonfd.getLimitFlight().isEmpty()) {
					row.createCell(4).setCellValue("所有");
					row.createCell(5).setCellValue("");
				}
				if (!baituonfd.getUnlimitFlight().isEmpty() && !baituonfd.getLimitFlight().isEmpty()) {
					System.out.println("eeeeeeeeeee");
				}
				row.createCell(6).setCellValue(
						((baituonfd.getWeekLimit() == null) || baituonfd.getWeekLimit().isEmpty()) ? "1234567"
								: baituonfd.getWeekLimit().replaceAll(",", ""));
				row.createCell(7).setCellValue(baituonfd.getCabin());
				row.createCell(8).setCellValue("指定票面价");// 票面价 返点
				row.createCell(9).setCellValue(baituonfd.getPrice());
				row.createCell(10).setCellValue("--返点");// 返点
				row.createCell(11).setCellValue("--留钱");// 留钱
				row.createCell(12).setCellValue(baituonfd.getSalseStartDate());
				row.createCell(13).setCellValue(baituonfd.getSalseEndDate());
				row.createCell(14).setCellValue(baituonfd.getFlightStartDate());
				row.createCell(15).setCellValue(baituonfd.getFlightEndDate());
				row.createCell(16).setCellValue("");// 起飞时间
				row.createCell(17).setCellValue(baituonfd.getAheadEarliest());
				row.createCell(18).setCellValue(baituonfd.getAheadLatest());
				row.createCell(19).setCellValue("0");// 改签说明
				row.createCell(20).setCellValue("test");// 舱位说明
				row.createCell(21).setCellValue("是");// 是否自动出票
				row.createCell(22).setCellValue("bjs410");// 带桥OFFICE号
				row.createCell(23).setCellValue("是");// 是否提供行程单
				row.createCell(24).setCellValue("0");// 退票规则
				row.createCell(25).setCellValue("0");// 改期规则
				row.createCell(26).setCellValue("否");// 是否容许签转
				row.createCell(27).setCellValue("否");// 是否提供常旅客积分
				row.createCell(28).setCellValue("0");// 证件类型
				row.createCell(29).setCellValue("");// 最大购买年龄
				row.createCell(30).setCellValue("");// 最小购买年龄
				r++;
			}
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String flightExchange(String flightLists, String arilines) {
		List<String> singleFlights = Lists.newArrayList();
		List<String> multiFlights = Lists.newArrayList();
		for (String flightList : flightLists.split("/")) {
			String flightOrg = flightList.substring(0, flightList.indexOf("-"));
			String flightDst = flightList.substring(flightList.indexOf("-") + 1);
			if (flightOrg.equals(flightDst)) {
				singleFlights.add(arilines + flightOrg);
			} else {
				multiFlights.add(arilines + flightOrg + "-" + arilines + flightDst);
			}
		}
		if (!singleFlights.isEmpty() && !multiFlights.isEmpty()) {
			return StringUtils.join(singleFlights, ",") + "," + StringUtils.join(multiFlights, ",");
		} else if (!singleFlights.isEmpty() && multiFlights.isEmpty()) {
			return StringUtils.join(singleFlights, ",");
		} else if (singleFlights.isEmpty() && !multiFlights.isEmpty()) {
			return StringUtils.join(multiFlights, ",");
		} else {
			return "";
		}
	}

	public static List<BaituoNfdData> readBaituExcel() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (baituoFileName.endsWith("xlsx")) {
			isE2007 = true;
		}
		List<BaituoNfdData> btnDatas = Lists.newArrayList();
		try {
			InputStream input = new FileInputStream(baituoFileName);
			// 根据文件格式(2003或者2007)来初始化
			Workbook wb = null;
			if (isE2007) {
				wb = new XSSFWorkbook(input);
				// wb = new HSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}
			Sheet sheet = wb.getSheetAt(0);
			int rows = 0;
			for (Row row : sheet) {
				rows++;
				if (rows <= 1) {
					continue;
				}
				BaituoNfdData btndata = new BaituoNfdData();
				int i = 0;
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell((short) j);
					i++;
					if (i == 1) {// 航空公司
						btndata.setArilines(cell.getStringCellValue());
					} else if (i == 2) {// 出发
						btndata.setDep(cell.getStringCellValue());
					} else if (i == 3) {// 到达
						btndata.setArr(cell.getStringCellValue());
					} else if (i == 4) {// 舱位
						btndata.setCabin(cell.getStringCellValue());
					} else if (i == 5) {// price
						btndata.setPrice(getIntString(cell.getNumericCellValue()));
					} else if (i == 6) {// 运价基础
						btndata.setDiscountCabin(cell.getStringCellValue());
					} else if (i == 7) {// 提前出票最早
						btndata.setAheadEarliest(cell.getNumericCellValue() + "");
					} else if (i == 8) {// 提前出票最晚
						btndata.setAheadLatest(cell.getNumericCellValue() + "");
					} else if (i == 9) {// 班期限制
						btndata.setWeekLimit(cell == null ? ""
								: (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? getIntString(cell
										.getNumericCellValue()) : cell.getStringCellValue()));
					} else if (i == 10) {// 适合航班
						btndata.setUnlimitFlight(cell == null ? ""
								: (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? getIntString(cell
										.getNumericCellValue()) : cell.getStringCellValue()));
					} else if (i == 11) {// 不适合航班
						btndata.setLimitFlight(cell == null ? ""
								: (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? getIntString(cell
										.getNumericCellValue()) : cell.getStringCellValue()));
					} else if (i == 12) {// 销售起始
						btndata.setSalseStartDate(sdf.format(cell.getDateCellValue()));
					} else if (i == 13) {// 销售截止
						btndata.setSalseEndDate(sdf.format(cell.getDateCellValue()));
					} else if (i == 14) {// 旅行起始
						btndata.setFlightStartDate(sdf.format(cell.getDateCellValue()));
					} else if (i == 15) {// 旅行结束
						btndata.setFlightEndDate(sdf.format(cell.getDateCellValue()));
					} else {
						System.out.println("null" + cell.getStringCellValue());
					}
				}
				btnDatas.add(btndata);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return btnDatas;
	}

	public static String getIntString(double d) {
		return Double.valueOf(d).intValue() + "";
	}
}
