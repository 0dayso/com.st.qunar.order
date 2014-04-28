package com.st.qunar.order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		String lines = FileUtils.readFileToString(new File("wjdfn.txt"));
		FileWriter fw = new FileWriter("wjdfnok.txt");
		for (String line : lines.split("【东风标致】\n")) {
			if (line.trim().isEmpty()) {
				continue;
			}
			String mob = line.substring(0, 11);
			String chezhu = line.substring(line.indexOf("尊敬的车主") + 5, line.indexOf("，"));
			String content = "尊敬的车主***" + line.substring(line.indexOf("，"));
			// System.out.println(mob + "," + chezhu + "," + content + "【东风标致】");
			fw.write(chezhu + "\n");
			fw.flush();
			// break;
		}
		fw.close();
	}
}
