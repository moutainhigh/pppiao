package com.fullcrum.service.impl.sys;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.TransactionDao;
import com.fullcrum.model.sys.TransactionEntity;
import com.fullcrum.service.sys.TransactionService;

@Service(value="transactionServiceImpl")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public ArrayList<Map<String, Object>> selectTransacByTransacId(String transactionId) {
		
		return transactionDao.selectTransacByTransacId(transactionId);
	}
	
	public String getIntentionStatusByTransacType(String orderId) {
		return transactionDao.getIntentionStatusByTransacType(orderId);
	}
	@Override
	public ArrayList<Map<String,Object>> selectTransacByBillNumber(String billNumber) {
		
		return transactionDao.selectTransacByBillNumber(billNumber);
	}

	@Override
	public ArrayList<Map<String, Object>> selectTransacByBuyerId(String buyerId) {
		
		return transactionDao.selectTransacByBuyerId(buyerId);
	}

	@Override
	public ArrayList<Map<String, Object>> selectTransacBySellerId(String sellerId) {
		
		return transactionDao.selectTransacBySellerId(sellerId);
	}

	@Override
	public void insertTransaction(TransactionEntity transactionEntity) throws Exception {
		Integer temp = UUID.randomUUID().hashCode();
		while (temp <= 0) {
			temp = UUID.randomUUID().hashCode();
		}
		transactionEntity.setTransactionId(temp.toString());
		
		transactionEntity.setTransacDate(new Date(new java.util.Date().getTime()));
		transactionDao.insertTransaction(transactionEntity);
	}

	@Override
	public void deleteTransaction(int transactionId) {
		transactionDao.deleteTransaction(transactionId);
	}

	@Override
	public void updateTransaction(TransactionEntity transactionEntity) {
		transactionDao.updateTransaction(transactionEntity);
	}

	@Override
	public void updateTransactionStatus(JSONObject jsonObject) {
		
		transactionDao.updateTransactionStatus(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectTransInfo(int transactionId) {
		return transactionDao.selectTransInfo(transactionId);
	}

	@Override
	public void updateTransStatus(int transactionId, String transStatus) {
		transactionDao.updateTransStatus(transactionId,transStatus);

	}

	@Override
	public List<Map<String,Object>> selectAllTrans(Integer pageSize,Integer currentPage) {
		return transactionDao.slectAllTrans(pageSize,currentPage);
	}

	//更新transaction表中的intentionStatus字段
	@Override
	public void updateTransactionIntentionStatus(JSONObject jsonObject) {
		
		transactionDao.updateTransactionIntentionStatus(jsonObject);
	}

	@Override
	public void setTransactionIntentionStatus(JSONObject jsonObject) {
		
		transactionDao.setTransactionIntentionStatus(jsonObject);
	}

	@Override
	public void updateTransStatus(String billNumber) {
		transactionDao.updateIntentionStatus(billNumber);
		
	}

	@Override
	public void insertTransaction(JSONObject transactionEntity) {
		
		transactionDao.insertTransactionJson(transactionEntity);
	}

	@Override
	public Integer getCount() {
		
		return transactionDao.getCount();
	}

	@Override
	public List<Map<String, Object>> selectOrderIdByBillNum(String billNumber) {
		return transactionDao.selectOrderIdByBillNum(billNumber);
	}

	@Override
	public void updateIntentionStatusByBillNum(JSONObject setTransacInvalid) {
		transactionDao.updateIntentionStatusByBillNum(setTransacInvalid);
	}

	@Override
	public List<Map<String, Object>> selectCountByIntentionStatus(JSONObject jsonObject) {
		return transactionDao.selectCountByIntentionStatus(jsonObject);
	}

	@Override
	public void setTransactionIntentionStatusByOrderId(JSONObject jsonObject) {
		
		transactionDao.setTransactionIntentionStatusByOrderId(jsonObject);
	}

	@Override
	public void updateTransactionRealMoney(JSONObject jsonObject) {
		transactionDao.updateTransactionRealMoney(jsonObject);
		
	}

	@Override
	public void updateTransactionIntentionStatusAndBuyerId(JSONObject jsonObject) {
		transactionDao.updateTransactionIntentionStatusAndBuyerId(jsonObject);
	}


}
