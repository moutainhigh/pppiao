package com.fullcrum.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.QuoteEntity;

@Mapper
public interface QuoteDao {

	String TABLE_NAME = "ppp_quote";
	String INSERT_FIELDS = " billNumber,quoterId,quoteAmount,interest,xPerLakh,status,quoteDate";
	
	@Select({"select  * from ",TABLE_NAME," where quoteId = #{quoteId}"})
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByQuoteId( @Param("quoteId") int quoteId);
	
	@Select({"select * from ",TABLE_NAME,"where quoterId = #{quoterId}"})
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByQuoterId(@Param("quoterId") String quoterId);
	
	@Select({"select * from ",TABLE_NAME,"where billNumber = #{billNumber} " })
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByBillNumber(@Param("billNumber") String billNumber);
	
	@Insert({"insert " ,TABLE_NAME,"(",INSERT_FIELDS," ) values(#{quoteEntity.billNumber} ,#{quoteEntity.quoterId},#{quoteEntity.quoteAmount},#{quoteEntity.interest},#{quoteEntity.xPerLakh},"
			+ "#{quoteEntity.status},#{quoteEntity.quoteDate} )"})
	public void insertQuote(@Param("quoteEntity") QuoteEntity quoteEntity);
	
	@Delete({"delete from ",TABLE_NAME,"where quoteId = #{quoteId}"})
	public void deleteQuoteByQuoteId(@Param("quoteId") int quoteId);
	
	@Update({"update ppp_quote set status = #{jsonObject.quoteStatus} where billNumber = #{jsonObject.billNumber} "})
	public void updateQuoteStatus(@Param("jsonObject") JSONObject jsonObject);
	
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId, c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getALLQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getAcceptedQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getUnderQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getFailQuote(@Param("jsonObject") JSONObject jsonObject);
	
	
	
}
