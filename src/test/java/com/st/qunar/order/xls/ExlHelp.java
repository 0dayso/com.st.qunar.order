package com.st.qunar.order.xls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.common.collect.Lists;

public class ExlHelp {

	private final static String filename = "国航4.25-27.xls";
	private final static String quNarFileName = "去哪儿4.25-27.xls";

	private final static String[] headers = { "ID", "标题", "价格", "数量", "描述" };

	private final static int rows = 65535;

	public static void main(String[] args) {
		// writeExcel();
		List<CaData> cadatas = readCaExcel();
		List<QunarData> qunars = readQunarExcel();

		List<CaData> succMatch = Lists.newArrayList();// 匹配了的
		List<CaData> failMatch = Lists.newArrayList();// 没有匹配的
		for (CaData cadata : cadatas) {
			// 按个比较
			String orgTicketNo = cadata.getTicketNo();
			QunarData qunarData = isExist(orgTicketNo, qunars);
			if (qunarData == null) {
				failMatch.add(cadata);
			} else {
				succMatch.add(cadata);
			}
		}
		// match
		System.out.println("succ match :");
		for (CaData cadata : succMatch) {
			System.out.println(cadata.getTicketNo());
		}
		System.out.println("==============");
		System.out.println("fail match");
		for (CaData cadata : failMatch) {
			System.out.println(cadata.getTicketNo());
		}
	}

	public static QunarData isExist(String ticketNo, List<QunarData> qunars) {
		QunarData qd = null;
		for (QunarData qunarData : qunars) {
			if (qunarData.getTicketNo().contains(ticketNo)) {
				return qunarData;
			}
		}
		return qd;
	}

	// public static void writeExcel() {
	// try {
	// Thread.sleep(1000 * 20);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	// Workbook wb = new HSSFWorkbook();
	// try {
	// FileOutputStream fileOut = new FileOutputStream(filename);
	//
	// Sheet sheet = wb.createSheet("poi测试");
	// Row row = sheet.createRow(0);
	// for (int i = 0; i < headers.length; i++) {
	// row.createCell(i).setCellValue(headers[i]);
	// }
	//
	// List<Record> records = TestUtil.getRecords(rows);
	// long s1 = System.nanoTime();
	// int r = 1;
	// for (Record record : records) {
	// row = sheet.createRow(r);
	// row.createCell(0).setCellValue(record.getId());
	// row.createCell(1).setCellValue(record.getTitle());
	// row.createCell(2).setCellValue(record.getPrice());
	// row.createCell(3).setCellValue(record.getQuantity());
	// row.createCell(4).setCellValue(record.getDesc());
	// r++;
	// }
	//
	// wb.write(fileOut);
	// fileOut.close();
	// long s2 = System.nanoTime();
	// System.out.println("poi write " + rows + " rows to excel:" + (s2 - s1));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public static List<QunarData> readQunarExcel() {
		List<QunarData> qunarDatas = Lists.newArrayList();
		try {
			InputStream inp = new FileInputStream(quNarFileName);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(sheet.getSheetName());

			int rows = 0;
			for (Row row : sheet) {
				if (rows == 0) {
					rows++;
					continue;
				}
				// 序号 票号 航段 舱位 航班号 航班日期 出票日期 票面金额(不含税) 税1 税2 税3 税总 代理费 代理费率 实际支付金额(含税) 订单编号 BPNR CPNR 出票人ID 支付银行 银行订单号
				// 退票手续费 退款金额 退票单号 EI TC 团/散 国内/国际 大客户编码 备注
				QunarData cadata = new QunarData();
				int i = 0;
				for (Cell cell : row) {
					i++;
					if (i == 1) {// 票号列
						cadata.setId(cell.getStringCellValue());
					} else if (i == 2) {// 订单编号
						cadata.setOrderNo(cell.getStringCellValue());
					} else if (i == 22) {// 票号
						cadata.setTicketNo(cell.getStringCellValue());
					}
					// else if (i == 4) {// 舱位
					// cadata.setCobin(cell.getStringCellValue());
					// } else if (i == 5) {// 航班号
					// cadata.setFlight(cell.getStringCellValue());
					// } else if (i == 6) {// 航班日期
					// cadata.setDate(cell.getStringCellValue());
					// } else if (i == 7) {// 出票日期
					// cadata.setCreTicketTime(cell.getStringCellValue());
					// } else if (i == 8) {// 票面金额(不含税)
					// cadata.setAmount(cell.getNumericCellValue() + "");
					// } else if (i == 9) {// 税1
					// cadata.setTax1(cell.getNumericCellValue() + "");
					// } else if (i == 10) {// 税2
					// cadata.setTax2(cell.getNumericCellValue() + "");
					// } else if (i == 11) {// 税3
					// cadata.setTax3(cell.getNumericCellValue() + "");
					// } else if (i == 12) {// 税总
					// cadata.setTaxAmount(cell.getNumericCellValue() + "");
					// } else if (i == 13) {// 代理费
					// cadata.setAgentAmount(cell.getNumericCellValue() + "");
					// } else if (i == 14) {// 代理费率
					// cadata.setAgentRate(cell.getNumericCellValue() + "");
					// } else if (i == 15) {// 实际支付
					// cadata.setPayAmount(cell.getNumericCellValue() + "");
					// } else if (i == 16) {// 订单编号
					// cadata.setOrderNo(cell.getStringCellValue());
					// } else if (i == 16) {// 订单编号
					// cadata.setOrderNo(cell.getStringCellValue());
					// } else if (i == 17) {// bpnr
					// cadata.setBpnr(cell.getStringCellValue());
					// } else if (i == 19) {// cpnr
					// cadata.setCpnr(cell.getStringCellValue());
					// } else if (i == 20) {// 出票人id
					// cadata.setPassengerId(cell.getStringCellValue());
					// } else if (i == 21) {// 支付银行
					// cadata.setPayBank(cell.getStringCellValue());
					// } else if (i == 22) {// 银行订单号
					// cadata.setBankOrderId(cell.getNumericCellValue() + "");
					// } else if (i == 23) {// 退票手续费
					// cadata.setRefundCharge(cell.getNumericCellValue() + "");
					// } else if (i == 24) {// 退票金额
					// cadata.setRefundAmount(cell.getStringCellValue() + "");
					// } else if (i == 25) {// 退票单号
					// cadata.setRefundTicketNo(cell.getStringCellValue());
					// } else if (i == 26) {// EI
					// cadata.setEi(cell.getStringCellValue());
					// } else if (i == 27) {// TC
					// cadata.setTc(cell.getStringCellValue());
					// } else if (i == 28) {// 团、散
					// cadata.setPassangerType(cell.getStringCellValue());
					// } else if (i == 29) {// 国内、国际
					// cadata.setTicketType(cell.getStringCellValue());
					// } else if (i == 30) {// 大客户编号
					// cadata.setBigCustomerNo(cell.getStringCellValue());
					// } else if (i == 31) {// 备注
					// cadata.setNote(cell.getStringCellValue());
					// }
				}
				qunarDatas.add(cadata);
			}
			inp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return qunarDatas;
	}

	public static List<CaData> readCaExcel() {
		List<CaData> cadatas = Lists.newArrayList();
		try {
			long s1 = System.nanoTime();
			InputStream inp = new FileInputStream(filename);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(sheet.getSheetName());
			int rows = 0;
			for (Row row : sheet) {
				rows++;
				if (rows <= 6) {
					continue;
				}
				// 序号 票号 航段 舱位 航班号 航班日期 出票日期 票面金额(不含税) 税1 税2 税3 税总 代理费 代理费率 实际支付金额(含税) 订单编号 BPNR CPNR 出票人ID 支付银行 银行订单号
				// 退票手续费 退款金额 退票单号 EI TC 团/散 国内/国际 大客户编码 备注
				CaData cadata = new CaData();
				int i = 0;
				for (Cell cell : row) {
					i++;
					if (i == 1) {// 票号列
						cadata.setId(cell.getNumericCellValue() + "");
					} else if (i == 2) {// 票号
						cadata.setTicketNo(cell.getStringCellValue());
					} else if (i == 3) {// 航段
						cadata.setRount(cell.getStringCellValue());
					} else if (i == 4) {// 舱位
						cadata.setCobin(cell.getStringCellValue());
					} else if (i == 5) {// 航班号
						cadata.setFlight(cell.getStringCellValue());
					} else if (i == 6) {// 航班日期
						cadata.setDate(cell.getStringCellValue());
					} else if (i == 7) {// 出票日期
						cadata.setCreTicketTime(cell.getStringCellValue());
					} else if (i == 8) {// 票面金额(不含税)
						cadata.setAmount(cell.getNumericCellValue() + "");
					} else if (i == 9) {// 税1
						cadata.setTax1(cell.getNumericCellValue() + "");
					} else if (i == 10) {// 税2
						cadata.setTax2(cell.getNumericCellValue() + "");
					} else if (i == 11) {// 税3
						cadata.setTax3(cell.getNumericCellValue() + "");
					} else if (i == 12) {// 税总
						cadata.setTaxAmount(cell.getNumericCellValue() + "");
					} else if (i == 13) {// 代理费
						cadata.setAgentAmount(cell.getNumericCellValue() + "");
					} else if (i == 14) {// 代理费率
						cadata.setAgentRate(cell.getNumericCellValue() + "");
					} else if (i == 15) {// 实际支付
						cadata.setPayAmount(cell.getNumericCellValue() + "");
					} else if (i == 16) {// 订单编号
						cadata.setOrderNo(cell.getStringCellValue());
					} else if (i == 16) {// 订单编号
						cadata.setOrderNo(cell.getStringCellValue());
					} else if (i == 17) {// bpnr
						cadata.setBpnr(cell.getStringCellValue());
					} else if (i == 19) {// cpnr
						cadata.setCpnr(cell.getStringCellValue());
					} else if (i == 20) {// 出票人id
						cadata.setPassengerId(cell.getStringCellValue());
					} else if (i == 21) {// 支付银行
						cadata.setPayBank(cell.getStringCellValue());
					} else if (i == 22) {// 银行订单号
						cadata.setBankOrderId(cell.getNumericCellValue() + "");
					} else if (i == 23) {// 退票手续费
						cadata.setRefundCharge(cell.getNumericCellValue() + "");
					} else if (i == 24) {// 退票金额
						cadata.setRefundAmount(cell.getStringCellValue() + "");
					} else if (i == 25) {// 退票单号
						cadata.setRefundTicketNo(cell.getStringCellValue());
					} else if (i == 26) {// EI
						cadata.setEi(cell.getStringCellValue());
					} else if (i == 27) {// TC
						cadata.setTc(cell.getStringCellValue());
					} else if (i == 28) {// 团、散
						cadata.setPassangerType(cell.getStringCellValue());
					} else if (i == 29) {// 国内、国际
						cadata.setTicketType(cell.getStringCellValue());
					} else if (i == 30) {// 大客户编号
						cadata.setBigCustomerNo(cell.getStringCellValue());
					} else if (i == 31) {// 备注
						cadata.setNote(cell.getStringCellValue());
					}
				}
				cadatas.add(cadata);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cadatas;
	}
}
