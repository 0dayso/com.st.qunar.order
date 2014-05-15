package com.st.qunar.order.xls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class QunarStatue {

	private final static String baituoFileName = "/Users/kxhu/Documents/trip/百拓/北京出港NFD运价5月4日早.xlsx";
	private final static String qunarFileName = "export (1).xls";

	private final static String[] qunarheads = { "航空公司", "政策代码", "起飞城市", "到达城市", "航班限制", "航班号", "班期限制", "适用舱位",
			"票面价类型", "" + "票面价/折扣", "返点", "留钱", "销售起始日期", "销售结束日期", "旅行起始日期", "旅行结束日期", "航班起飞时间", "最晚提前出票时限",
			"最早提前出票时限", "" + "退改签说明", "舱位说明", "是否自动出票", "搭桥office号", "是否提供行程单", "退票规则", "改期规则", "是否允许签转", "是否提供常旅客积分",
			"" + "证件类型", "最大购买年龄", "最小购买年龄" };

	public static void main(String[] args) {
		// writeExcel();
		Map<String, QunarData> ticketMap = Maps.newHashMap();
		List<QunarData> qunarDatas = readQnExcel();
		for (QunarData qd : qunarDatas) {
			// System.out.println(qd.getTicketNo() + ":" + qd.getOrderDate() + ":" + qd.getAirLines() + ":"
			// + qd.getFlight() + ":" + qd.getDepDate() + ":" + qd.getCobin());
			for (String tn : qd.getTicketNo().split(",")) {
				ticketMap.put(tn, qd);
				// System.out.println(tn + "::" + qd);
			}
		}
		List<String> st2 = readQnExcelSheet2();
		for (String st : st2) {
			// System.out.println(st);
			QunarData qd = ticketMap.get(st);
			if (qd != null) {
				System.out.println(st + "," + qd.getOrderNo() + "," + qd.getOrderDate() + "," + qd.getAirLines() + ","
						+ qd.getFlight() + "," + qd.getDepDate() + "," + qd.getCobin());
			}
		}
		// writeExcel(st2);
		System.out.println("write ok");
	}

	public static void writeExcel(List<BaituoNfdData> baitunfds) {
		Workbook wb = new HSSFWorkbook();
		try {
			FileOutputStream fileOut = new FileOutputStream("match.xls");
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
					String str = "";// flightExchange(baituonfd.getUnlimitFlight(), baituonfd.getArilines());
					row.createCell(5).setCellValue(str);
				} else if (!baituonfd.getLimitFlight().isEmpty()) {// 不适用航班不为空
					row.createCell(4).setCellValue("不适用");
					String str = "";// flightExchange(baituonfd.getLimitFlight(), baituonfd.getArilines());
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

	public static List<QunarData> readQnExcel() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (qunarFileName.endsWith("xlsx")) {
			isE2007 = true;
		}
		List<QunarData> btnDatas = Lists.newArrayList();
		try {
			InputStream input = new FileInputStream(qunarFileName);
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
				QunarData btndata = new QunarData();
				int i = 0;
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell((short) j);
					i++;
					if (i == 1) {//
						btndata.setOrderNo(cell.getStringCellValue());
					} else if (i == 2) {//
						btndata.setOrderDate(cell.getStringCellValue());
					} else if (i == 7) {//
						btndata.setRount(cell.getStringCellValue());
					} else if (i == 10) {// 舱位
						btndata.setAirLines(cell.getStringCellValue());
					} else if (i == 11) {// price
						btndata.setFlight(cell.getStringCellValue());
					} else if (i == 12) {// 运价基础
						btndata.setDepDate(cell.getStringCellValue());
					} else if (i == 13) {// 提前出票最早
						btndata.setCobin(cell.getStringCellValue());
					} else if (i == 18) {// 旅行结束
						btndata.setTicketNo(cell.getStringCellValue());
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

	public static List<String> readQnExcelSheet2() {
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (qunarFileName.endsWith("xlsx")) {
			isE2007 = true;
		}
		List<String> st2 = Lists.newArrayList();
		try {
			InputStream input = new FileInputStream(qunarFileName);
			// 根据文件格式(2003或者2007)来初始化
			Workbook wb = null;
			if (isE2007) {
				wb = new XSSFWorkbook(input);
				// wb = new HSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}
			Sheet sheet = wb.getSheetAt(1);
			int rows = 0;
			for (Row row : sheet) {
				rows++;
				if (rows <= 1) {
					continue;
				}
				Cell cell = row.getCell(0);
				st2.add(cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? getIntString(cell.getNumericCellValue()) : cell
						.getStringCellValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return st2;
	}

	public static String getIntString(double d) {
		return Double.valueOf(d).intValue() + "";
	}
}
