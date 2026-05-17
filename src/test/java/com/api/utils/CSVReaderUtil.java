package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.beans.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private CSVReaderUtil() {
		// Private constructor
	}

	public static void loadCSV(String pathofFile) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathofFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);

		// Below code is for map CSV to UserBean POJO's

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader).withType(UserBean.class)
				.withIgnoreEmptyLine(true).build();

		List<UserBean> userList = csvToBean.parse();
		System.out.println(userList);
	}

}
