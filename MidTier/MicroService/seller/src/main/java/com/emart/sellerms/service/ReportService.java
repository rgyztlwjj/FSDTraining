package com.emart.sellerms.service;

import java.text.ParseException;
import java.util.List;

import com.emart.sellerms.models.*;

public interface ReportService {

	public List<ReportModel> getReport(Integer userId);

	List<ReportModel> getReport(Integer sellerId, String item, String strFromDate, String strToDate)
			throws ParseException;
}