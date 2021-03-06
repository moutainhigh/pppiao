package com.fullcrum.controller.sys;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.fullcrum.model.sys.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.QuoteEntity;
import com.fullcrum.service.sys.MsgService;
import com.fullcrum.service.sys.QuoteService;
import com.fullcrum.service.sys.TransactionService;
import com.fullcrum.utils.GoEasyAPI;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/ppp/quote")
public class QuoteController {

	@Resource(name="quoteServiceImpl")
	private QuoteService quoteService;
	
	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;
	
	@Resource(name="msgServiceImpl")
	private MsgService msgService;
	
	@Autowired
	private GoEasyAPI goEasyAPI;
	
	/*根据报价id 查询报价详情*/
	@RequestMapping("/getByQuoteId")
	public ArrayList<QuoteEntity>  getQuoteByQuoteId(@RequestParam(value="quoteId") int quoteId){
		
		return quoteService.selectQuoteByQuoteId(quoteId);
	}
	
	/*根据报价  者 id 查询报价详情*/
	@RequestMapping("/getByQuoterId")
	public ArrayList<QuoteEntity> getQuoteByQuoterId( @RequestParam(value="quoterId") String quoterId){
		return quoteService.selectQuoteByQuoterId(quoterId);
	}
	
	/*根据报 票据的 票号 billNumber  查询报价详情*/
	@RequestMapping("/getByBillNumber")
	public ArrayList<QuoteEntity> getQuoteByBillNumber(@RequestParam(value="billNumber") String billNumber ){
		return quoteService.selectQuoteByBillNumber(billNumber);
	}

	@RequestMapping("/getByBillNumberAndQuoterId")
	public ArrayList<QuoteEntity> getQuoteByBillNumberAndQuoterId(@RequestParam(value="billNumber") String billNumber,@RequestParam(value="quoterId") String quoterId ){
		return quoteService.selectQuoteByBillNumberAndQuoterId(billNumber,quoterId);
	}
	
	/*增加 报价 */
	@ApiOperation(value="增加报价",notes="增加报价，并向发票人发送消息")
	@RequestMapping("/addQuote")
	public JSONObject addQuote(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		JSONObject msgInfo = jsonObject.getJSONObject("message");
		String channel = msgInfo.getString("receiverId");
		String message = msgInfo.toJSONString();
		JSONObject quoteEntity = jsonObject.getJSONObject("quoteEntity");
		

		try {
			quoteService.insertQuote(quoteEntity);
			msgService.insertMsg(msgInfo);
			goEasyAPI.sendMessage(channel, message);
			System.out.println(channel);
			result.put("status", "success");
			result.put("errorMsg", null);
		} catch (Exception e) {
			result.put("status", "fail");
			result.put("errorMsg",null);
		}
		
		return  result;
		
	}
	
	/*根据报价id 删除 报价 */
	@RequestMapping("/deleteQuote")
	public String deleteQuote(@RequestParam(value="quoteId") int quoteId) {
		
		quoteService.deleteQuoteByQuoteId(quoteId);
		return "success";
	}
	
	
	/*根据筛选条件 查询报价详情
	 * 
	 * 当filter字段 为  1 时，查询全部报价
	 * 当filter字段为2时 ，查询 已经被接受的报价
	 * 当filter字段为3时，查询正在报价的报价
	 * 当filter字段为4时，查询失败的报价
	 * 
	 * */
	@RequestMapping("/getMyQuote")
	public List<Map<String, Object>> getMyQuote(@RequestBody JSONObject jsonObject){
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Integer currentPage = jsonObject.getInteger("currentPage");
		Integer pageSize = jsonObject.getInteger("pageSize");
		jsonObject.put("currentPage", (currentPage-1)*pageSize);
		switch (jsonObject.get("filter").toString()) {
		case "1":
			//全部报价
/*			System.out.println("看看时间是个什么鬼");
			List<Map<String, Object>> tt = quoteService.getALLQuote(jsonObject);
			System.out.println(tt.get(0).get("releaseDate"));*/
			return quoteService.getALLQuote(jsonObject);
		case "2":
			return quoteService.getAcceptedQuote(jsonObject);
		case "3":
			//报价中
			return quoteService.getUnderQuote(jsonObject);
		case "4":
			//已失效
			return quoteService.getFailQuote(jsonObject);
		default:
			return null;
		}
	}
	//获取条数
	@RequestMapping("/getQuoteCount")
	public Integer getQuoteCount(@RequestBody JSONObject jsonObject){
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		switch (jsonObject.get("filter").toString()) {
		case "1":
			//全部报价
			return quoteService.getALLQuoteCount(jsonObject);
		case "2":
			//return quoteService.getAcceptedQuote(jsonObject);
		case "3":
			//报价中
			return quoteService.getUnderQuoteCount(jsonObject);
		case "4":
			//已失效
			return quoteService.getFailQuoteCount(jsonObject);
		default:
			return null;
		}
	}
	
	/*  
	 * 增加交易意向
	 * 
	 * */
	@RequestMapping("/submitIntention")
	public JSONObject updateQuoteStatus(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		//quoteService.updateQuoteStatus(jsonObject);
		//卖家确定某一个买家的报价
		
		//更新交易信息
		
		result.put("status", "success");
		
		return result;
	}
	
	//通过票据号码来查询我的报价页面票据详情的信息
	@RequestMapping("/getDetail")
	public List<Map<String,Object>> getDetail(@RequestParam("billNumber")String billNumber){
		System.out.println(billNumber);
		List<Map<String,Object>> list = quoteService.selectBillByBillNum(billNumber);
		for (Map<String, Object> map : list) {
				Object object = map.get("pic1");
				System.out.println(object);
		}
		
		return list;
	}
	
	/*修改quote表中real_money字段，即实际价格
	 * jsonobject 字段：
	 * billNumber
	 * quoterId
	 * new_money
	 * 
	 * */
	@RequestMapping("/updateRealMoney")
	public JSONObject updateRealMoney(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		try {
			quoteService.updateRealMoney(jsonObject.getJSONObject("quoteBody"));
			transactionService.updateTransactionRealMoney(jsonObject.getJSONObject("transactionBody"));
			result.put("status", "success");
			result.put("errorMsg", null);
		} catch (Exception e) {
			
			result.put("status", "fail");
			result.put("errorMsg", e);
		}
		
		return result;
	}

	@RequestMapping("/updateStatus")
	public JSONObject updateStatusByBillNumberAndStatus(@RequestBody JSONObject jsonObject){
		JSONObject result = new JSONObject();
		JSONObject transobj = new JSONObject();
		Integer randomNum = UUID.randomUUID().toString().hashCode();
		while (randomNum < 0) {
			randomNum = UUID.randomUUID().toString().hashCode();
		}
		String orderId = 1+String.format("%015d", randomNum);
		transobj.put("transactionType", orderId);
		transobj.put("billNumber", jsonObject.getJSONObject("billInfo").getString("billNumber"));
		transobj.put("buyerId", null);
		transobj.put("sellerId", jsonObject.getJSONObject("billInfo").getString("releaserId"));
		transobj.put("amount", jsonObject.getJSONObject("billInfo").getString("amount"));
		transobj.put("transactionStatus", "1");
		transobj.put("transacDate", jsonObject.getJSONObject("billInfo").get("releaseDate"));

		String channel = jsonObject.getJSONObject("message").getString("receiverId");
		try{
			quoteService.updateStatusByBillNumAndStatus(jsonObject.getJSONObject("quoteBack"));
			quoteService.updateStatusByBillNumAndStatus(jsonObject.getJSONObject("setQuoteInvalid"));
			transactionService.updateIntentionStatusByBillNum(jsonObject.getJSONObject("setTransacInvalid"));
			transactionService.insertTransaction(JSONObject.toJavaObject(transobj, TransactionEntity.class));
			msgService.insertMsg(jsonObject.getJSONObject("message"));
			goEasyAPI.sendMessage(channel,jsonObject.getJSONObject("message").toJSONString());
			result.put("status","success");
			return result;
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.put("status","failed");
			return result;
		}
	}
}
