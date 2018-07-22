<%@page import="com.glnetcomsolutions.web.InvoiceMaster"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
		
		String discountDisplayProp = "";
		%>
<style type="text/css">
#overallTotalTable{
	float:right;
	width:50%;
}
.headerclass{
	background-color:rgb(102, 102, 102);
	border-bottom:solid 1px black;
}
.hd,
.hd td,
.hd th
{
	background-color:rgb(102, 102, 102);
	border-bottom:solid 1px black;
}
.hd td input
{
	background-color:rgb(102, 102, 102);
	height:18px;
	text-align:right;
	border:solid 1px #666;
	color:#fff;
}
.logo{
    background-position:-150px -7px;
    width:257px;
    height:57px;
}
#invNumber{
width:80px;
float:left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>J.S.</title>
<link href="ig/free-invoice.css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
<link rel="stylesheet" href="css/ui.jqgrid.css" type="text/css" />
<link href="ig/print.css" rel="stylesheet" media="print">
<!-- JavaScripts -->
<script type="text/javascript" async="" src="ig/ga.js"></script>
<!-- <script type="text/javascript" src="ig/zoho-invoice-index.js">-->
<script type="text/javascript" src="js/Invoice.js">
	
</script>
 <script src="js/lib/jquery-2.1.0.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>

<script src="js/grid.locale-en.js"></script>
<script src="js/jquery.jqGrid.min.js"></script>

<script src="js/invoicemaster.js"></script>
<script src="js/Invoice.js"></script>

<script type="text/javascript">
var itemDetail = <%=InvoiceMaster.getAllProductDetail()%>
var taxDetail = <%=InvoiceMaster.getAllTaxDetail()%>
var customerDetails = <%=InvoiceMaster.getCustomerDetail()%>
</script>
<style type="text/css">
</style>
</head>

<body>
	<div class=outerDiv>
		<%@ include file="navbar.jsp" %>
		<div class="b-div">
			<div class="body-div">

				<div class="main-div">

					<div class="rgt-main-div">
			
						<div>
						<div id="header">
						
						</div>




						
							
											<input id="invoiceTextBox"
											class="c-name" type="text" value="" style="width:100%;text-align:left;display:none;" tabindex-old="5"
											onfocus="javascript:CreateInvoiceUtil.showHelpText(6)">
											<input id="invoiceTextBox"
											class="c-name" type="text" value="INVOICE" style="font-weight:bold;width:100%;" tabindex-old="5"
											onfocus="javascript:CreateInvoiceUtil.showHelpText(6)"><br>
											
				<ul style="width: 100%; margin-top: 2px;margin-bottom:0px;">
								<li class="adr-lft">
								<!--  change this -->
								<input type="text" value="Bill To:" disabled="disabled"
									id="billToLabel" class="adr bill-to bld" tabindex="6"
									onfocus="javascript:CreateInvoiceUtil.showHelpText(1)">
									<br> <input type="text" id="billingAddress1" class="adr"
									tabindex="6" placeholder="Customer's Name"
									onfocus="javascript:CreateInvoiceUtil.showHelpText(1)"
									onblur="javascript:CreateInvoiceUtil.updateInvoiceSubject(this)">
									<br> <input type="text" id="billingAddress2" class="adr"
									tabindex="7" placeholder="Customer's Address"
									onfocus="javascript:CreateInvoiceUtil.showHelpText(1)">
									<br> <input type="text" class="adr" id="billingAddress3"
									tabindex="8" placeholder="City, State Zip"
									onfocus="javascript:CreateInvoiceUtil.showHelpText(1)">
									<br> <input type="text" class="adr" tabindex="10" style="display:none;"
									id="billingAddress5" placeholder="Country"
									onfocus="javascript:CreateInvoiceUtil.showHelpText(1)">
									<input type="text" class="adr" tabindex="10" style=""
									id="mobileNumber" placeholder="Mobile Number"
									onkeypress="return numeralsOnly(event)" onfocus="javascript:CreateInvoiceUtil.showHelpText(1)">
									<br></li>
								<li class="adr-rgt">
									<table width="100%" cellpadding="0" cellspacing="0"
										class="bill">
										<tbody>
											<tr>
												<td class="lft-txt" width="40%"><input type="text"
													value="Invoice#" disabled="disabled" id="invNumberLabel" class="bld"
													tabindex="12"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
												<td><input type="text" id="invNumber" tabindex="13"
													value="<%=InvoiceMaster.getUniqueInvoiceNumber() %>"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
											</tr>
											<tr>
												<td class="lft-txt"><input type="text"
													value="Invoice Date" disabled="disabled" id="invoiceDateLabel" class="bld"
													tabindex="14"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
												<td><input type="date" id="invoiceDate" tabindex="15"
													placeholder="05 Feb 2013"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
											</tr>
											<tr>
											</tr>
											<tr>
												<td class="lft-txt"><input value="Due Date"
													id="dueDateLabel" disabled="disabled" class="bld" type="text" tabindex="16"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">

												</td>
												<td><input id="dueDate" tabindex="17" type="date"
													placeholder="20 Feb 2013"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
											</tr>
											<tr style="">
												<td class="lft-txt"><input value="Sales Man" disabled="disabled"
													id="salesmannamelabel" class="bld" type="text" 
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">

												</td>
												<td><input id="salesManName" tabindex="17" type="text"
													placeholder="Sales Man Name"
													onfocus="javascript:CreateInvoiceUtil.showHelpText(2)">
												</td>
											</tr>
										</tbody>
									</table>
								</li>
							</ul>
						</div>
						<div style=" float: left; width: 99%;margin-top: 20px;">
							<input placeholder="Dear Client," id="attID" tabindex-old="18"
								style="width: 99%">
						</div>
						<div style="clear: both;"></div>
						<div class="lineItemDIV" style="width:100%;">
							<table width="100%" cellpadding="0" cellspacing="0" class="column">
    <thead>
    	<tr class="hd headerclass" id="header_row">
    		<td  class="Itemserial-col" >
             <input style="text-align:center;border:none;"  type="text" id="itemSerial" value="S.No." class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
  			
            </td>  
    		<td  class="Itemcode-col" >
             <input style="text-align:center;border:none;"  type="text" id="itemCodeLabel" value="Select Product" class="bld hide_me_in_print" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
  			
            </td>
                    		
            <td  class="desc-col">
            <input style="text-align:center;border:none;text-align: center;" type="text" id="itemDescLabel" value="Item Name" class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            </td>
            <td  class="qty-col">
            	<input style="border:none;text-align: center;" type="text" value="Qty" id="itemQtyLabel" class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            </td>
            <td  class="rat-col">
            	<input style="border:none;text-align: center;" type="text" value="Rate" id="itemRateLabel" class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            </td>
            <td  class=" rat-col Itemcode-col" style="width:50px;display:<%= displayProp%>;">
            	<input style="border:none;text-align: center;" type="text" value="P. Rate" id="itemPurchasePrice" class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            </td>
                 <td  class="discout-r" style="display:<%= discountDisplayProp%>;">
            	<input style="border:none;text-align: center;" type="text" value="disc %" id="itemRateLabel" class="bld" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            	
            </td>
            
            <td  class="amo-col" style="text-align: center;">
            	<input  type="text" value="Amount" id="itemAmtLabel" class="bld" style="text-align:center;border:none;" tabindex-old="19" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" disabled="disabled">
            </td>
            <td style="border-bottom:none;background:none;border-top:none;width:20px;" class="dele-icon" style="width:20px;border:none;">&nbsp;</td>
          </tr>
        </thead>

        <tbody class="lineItems">
          <tr class="row-item trClone hide" id="lineItem.0" onclick="javascript:CreateInvoiceUtil.showHelpText(3)" onmouseover="javascript:CreateInvoiceUtil.showCloseIcon(this,true)" onmouseout="javascript:CreateInvoiceUtil.showCloseIcon(this,false)">
          <td class="Itemserial-col" style="border-right:solid 1px black;">
				<input type="text" value="0" id="itemSerial.0">
            </td>
           <td class="Itemcode-col hide_me_in_print" style="border-right:solid 1px black;">
            	<!-- <input  name="itemcode" class="lastLineItem" placeholder="punch bar code here" tabindex-old="20" id="itemCode.0" onkeyup="javascript:showItemDetailByCode(this,true);" onblur="javascript:showItemDetailByCode(this,false);">    -->        	
           		<select class="lastLineItem" tabindex-old="24" id="itemCode.0" onchange="javascript:showItemDetailByCode(this,false);">
           		
           		</select>
            </td>
             
            <td class="desc-r" style="border-right:solid 1px black;border-left:solid 1px black;">
            	<textarea type="text" tabindex-old="24" id="itemDesc.0" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)"  placeholder="Enter item name/description" onblur="javascript:showItemDetailByCode(this,true);" style="" class="desc_textarea"></textarea>
            </td>
            <td class="qty-r" style="border-right:solid 1px black;">
            	<input type="text" value="1" tabindex-old="24" id="itemQty.0" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">            	
            </td>
            <td class="rat-r" style="border-right:solid 1px black;">
            	<input type="text" value="0.00" tabindex-old="24" id="itemRate.0" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
            </td>
            <td class="rat-col Itemcode-col" style="border-right:solid 1px black;display:<%= displayProp%>;">
            	<input type="text" value="0.00" tabindex-old="24" id="itemPurchase.0" disabled="disabled" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
            </td>
             <td class="discout-r rat-r" style="border-right:solid 1px black;display:<%= discountDisplayProp%>;">
            	<input type="text" value="0.00" tabindex-old="24" id="itemDiscount.0" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
            	<input type="hidden" id="taxid.0" value="">
            	<input type="hidden" id="taxvalue.0" value="">
            	<input type="hidden" id="discvalue.0" value="0">
            </td>
            
            <td class="amo-r" style="border-right:solid 1px black;">
            	<span style="cursor:not-allowed;margin:0px;" id="itemTotal.0" title="Amount is calculated automatically.">0.00</span></td>
            
            <td style="border-bottom:none;background:none;border-top:none" class="dele-icon">
              <div style="padding-top:4px"><a class="closeicon hide" style="cursor:pointer" title="Delete Row" id="itemClose.0" onclick="javascript:CreateInvoiceUtil.removeLineItem(this)"><span class="del fl"></span></a></div>          
           </td>
         </tr>

     
       <tr class="row-item  " onclick="javascript:CreateInvoiceUtil.showHelpText(3)" id="lineItem.1" onmouseover="javascript:CreateInvoiceUtil.showCloseIcon(this,true)" onmouseout="javascript:CreateInvoiceUtil.showCloseIcon(this,false)">
       		 <td class="Itemserial-col" style="border-right:solid 1px black;">
					<input type="text" value="1" id="itemSerial.0">
            </td>
       		 <td class="Itemcode-col hide_me_in_print" style="border-right:solid 1px black;">
            	<!-- <input  placeholder="punch bar code here" name="itemcode" tabindex-old="23" id="itemCode.1" onkeyup="javascript:showItemDetailByCode(this,true);" onblur="javascript:showItemDetailByCode(this,false);" class="lastLineItem">     -->      	
           		<select class="lastLineItem " tabindex-old="20" id="itemCode.1" onchange="javascript:showItemDetailByCode(this,false);">
           		
           		</select>
            </td>
            
        <!--      <td class="desc-r" style="border-right:solid 1px black;border-left:solid 1px black;">
              <textarea type="text" class="lastLineItem" tabindex-old="23" placeholder="Enter item name/description" onkeypress="javascript:CreateInvoiceUtil.checkAndAddNewLineItem(this, event)" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" id="itemDesc.1" style="padding-top: 0px; padding-bottom: 0px; height: 17px; overflow: hidden;width:236px;height: 20px;"></textarea>
            </td>-->
            <td class="desc-r" style="border-right:solid 1px black;border-left:solid 1px black;">
              <textarea type="text" class="lastLineItem desc_textarea" tabindex-old="23" placeholder="Enter item name/description" onfocus="javascript:CreateInvoiceUtil.showHelpText(3)" id="itemDesc.1" onblur="javascript:showItemDetailByCode(this,true);" ></textarea>
            </td>
            <td class="qty-r" style="border-right:solid 1px black;">
            	<input type="text" value="1" id="itemQty.1" tabindex-old="23" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
            </td>
            <td class="rat-r" style="border-right:solid 1px black;">
            	<input type="text" value="0.00" id="itemRate.1" tabindex-old="23" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this); tempFixOfIndex(this);">
            </td>
            <td class="rat-col Itemcode-col" style="border-right:solid 1px black;display: <%= displayProp%>;">
            	<input type="text" value="0.00" tabindex-old="24" id="itemPurchase.1" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
            </td>
            <td class="discout-r rat-r" style="border-right:solid 1px black;display:=discountDisplayProp">
            	<input type="text" value="0.00" id="itemDiscount.1" tabindex-old="23" onblur="javascript:CreateInvoiceUtil.calculateItemTotal(this)">
           		<input type="hidden" id="taxid.1" value="">
            	<input type="hidden" id="taxvalue.1" value="">
            	<input type="hidden" id="discvalue.1" value="0">
            </td>
          
            <td class="amo-r" style="border-right:solid 1px black;">
            	<span style="cursor:not-allowed;margin:0px;" id="itemTotal.1" title="Amount is calculated automatically.">0.00</span></td>
            
            <td style="border-bottom:none;background:none;border-top:none" class="dele-icon">
              <div style="padding-top:4px"><a class="closeicon hide" id="itemClose.1" title="Delete Row" style="cursor:pointer;text-decoration:underline;color:#DE7110;" onclick="javascript:CreateInvoiceUtil.removeLineItem(this)"><span class="del fl">&nbsp;X</span></a></div>
          </td>
      </tr>
      </tbody>
      <tbody>
        <tr>
          <td >
           	
            <div style="float:left;width:99%;">
            	 <a style="cursor:pointer;margin-left:0px;margin-top: -16px;" title="Add Row" onclick="javascript:CreateInvoiceUtil.addInvoiceLineItem()"><span class="add fl">Add Line Item</span></a>
			
          
            </div>
          </td >

         <td colspan="6" style="border-right:none;">
         <div style="width:60%;"></div>          
            	  <table cellpadding="0" cellspacing="0" class="totalTable" style="" id="overallTotalTable">
    				      <tbody>
    				     <tr class="sav-amo txt-rgt" id="subtotal_row" style="">
			        	    <td class="bdr-non" style="text-align: left;">
                        	Total discount
                        <!-- 	<input type="text" value="Total Discount" id="totalDiscountLabel" tabindex-old="24" onfocus="javascript:CreateInvoiceUtil.showHelpText(9, this)">
            			-->
            			  </td>
            			  <td class="bdr-non" style="">
                        	<span style="cursor:not-allowed;font-weight:normal" class="amount" id="totalDiscount" ></span>
           				  </td>
       				    </tr>
    				      <tr class="sav-amo txt-rgt" id="subtotal_row" style="">
			        	    <td class="bdr-non" style="text-align: left;">
			        	    Sub total
                        	<!--  <input type="text" value="Sub Total" id="subTotalLabel" tabindex-old="24" onfocus="javascript:CreateInvoiceUtil.showHelpText(9, this)">
            			  -->
            			  </td>
            			  <td class="bdr-non" style="">
            			  
                        	<span style="cursor:not-allowed;font-weight:normal" class="amount" id="subTotal" title="Sub total is calculated automatically."></span>
           				  </td>
       				    </tr>
       				     <tr class="sav-amo txt-rgt" id="tax_table_row" style="">
       				     <td colspan="2">
			        	    <table id="tax_table">
			        	    	
			        	    </table>
			        	    </td>
       				    </tr>
                  <tr class="txt-rgt" id="labour_row" style="display:none;">
			        	  <td >
                        <input type="text" value="VAT (0%)" tabindex-old="25" id="taxLabel" onblur="javascript:CreateInvoiceUtil.calculateInvTaxAndTotal(this)" onfocus="javascript:CreateInvoiceUtil.showHelpText(4)" style="display:none;">
            		  	 <input type="text" value="Labour" tabindex-old="25" id="labourLabel" onblur="javascript:CreateInvoiceUtil.calculateInvTaxAndTotal(this)" onfocus="javascript:CreateInvoiceUtil.showHelpText(4)" style="">
            		  	</td>
            			  <td style="">
                       <span style="cursor:not-allowed;" class="amount bld" id="taxAmt" title="Tax amount is calculated automatically." style="display:none;"></span>
           				 <input type="text" value="0" class="amount bld" tabindex-old="25" id="labourText" onblur="javascript:CreateInvoiceUtil.calculateInvTaxAndTotal(this)" onfocus="javascript:CreateInvoiceUtil.showHelpText(4)" style="">
            		  	
           				 </td>
       				 </tr>
               <tr class="tot" style="background:#e3e3e3;">
			        	  <td style="text-align: left;">
            				<input type="text" value="TOTAL (INR)" id="totalLabel" style="border:none;" tabindex-old="26" onfocus="javascript:CreateInvoiceUtil.showHelpText(9, this)">
            			</td>
            			<td>
                		    <input type="text" class="currencycodetxt" maxlength="5" tabindex-old="27" id="currencyCode"  style="border:none;display:none;" value="INR">
                		    &nbsp;<span style="cursor:not-allowed;" class="amount bld" id="total" title="Amount is calculated automatically."></span>&nbsp;
           				 </td>
       				 </tr>
             </tbody></table>   
          </td>

        </tr>
       
        </tbody><tbody>
    </tbody></table>
						</div>
						
						<div id="footer">
						</div>
						<div class="power" style="display: ;">
							
							<ul style="width: 100%; margin-top: 2px;margin-bottom:-20px;">
									<li><span>Powered by</span></li>
									<li>
										<div class="powered-by">
										9 Cube.in &nbsp;&nbsp;
										
											<!-- <img src="http://9cube.in/wp-content/uploads/2014/09/logo2.png" alt="9 Cube" id="logo" style="height:30px;">-->
										</div>
									</li>
								</ul>
							
						</div>
						
					</div>

					<div class="lft-main-div" style="position:fixed; top: 120px;left: 80%;height: 200px;width: 201px;">
						<a href="#"
							onclick="saveandPrintInvoice();" class="print" style=""
							><span class="print-icon"></span><span
							class="prt-txt">Print</span></a>
							<br>
							<a href="#"
							onclick="location.reload();" class="print" style="margin-top:50px;"
							><span class="reset-icon"></span><span
							class="prt-txt">Reset</span></a>
							<a href="#"
							onclick="printOnly();" class="print" style="margin-top:120px;display:<%=displayButton %>;"
							><span class="print-icon"></span><span
							class="prt-txt">Print Only</span></a>

					</div>
					<div
						style="clear: both; margin-right: 40px; margin-top: 10px; float: right; color: #fff;"
						class="cont">
						<p>
							Need Help? <a
								style="text-decoration: underline; font-szie: 14px; color: #fff;"
								href="https://9cube.in/support.html">Contact
								us</a>
						</p>
					</div>



				</div>

				
				<div></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//ready();
	</script>
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
		
	</script>
	<script>
	var appliedTaxes = null;
	var itemCodeList = new Array();
	var itemName = new Array();
		window.onscroll = scroll;
		function scroll() {
			return;
			var windowHeight = $(document).height();
			var position = window.pageYOffset;

			if (position > 50) {
				if (position > (windowHeight - 1000)) {
					$(".print").css("position", "absolute");
					$(".print").css("top", "106px");
				} else {
					$(".print").css("position", "fixed");
					$(".print").css("top", "30px");
				}
			} else {
				$(".print").css("position", "absolute");
				$(".print").css("top", "106px");
			}
		}
		console.log("date set");
		var now = new Date();
		var month = (now.getMonth() + 1);               
		var day = now.getDate();
		if(month < 10) 
		    month = "0" + month;
		if(day < 10) 
		    day = "0" + day;
		
		var today = now.getFullYear() + '-' + month + '-' + day;
	//	today = day+"-"+month+"-"+now.getFullYear();
		$( "#invoiceDate" ).val(today);
		
		var duedate = new Date();
		duedate.setDate(duedate.getDate() + 15);
		var month = (duedate.getMonth() + 1);               
		var day = duedate.getDate();
		if(month < 10) 
		    month = "0" + month;
		if(day < 10) 
		    day = "0" + day;
		var duedateVal =  duedate.getFullYear() + '-' + month + '-' + day;
		$( "#dueDate" ).val(duedateVal);
		
		var itemCodeList = [];
		for(var i = 0 ; i< itemDetail.length;i++){
			itemCodeList.push(itemDetail[i]["item_desc"].toLowerCase());
		}
		console.log("itemCodeList : "+JSON.stringify(itemCodeList));
		itemCodeList.sort();
		console.log("itemCodeList : "+JSON.stringify(itemCodeList));
		var str = "<option value='New Item''>New Item</option>";
		for(var i = 0 ; i< itemCodeList.length;i++){
			str += "<option value='"+itemCodeList[i]+"'>"+itemCodeList[i]+"</option>";
			itemName.push(itemDetail[i]["item_desc"]);
		}
		                   $( "#itemCode\\.1" ).html(str);
		                   $( "#itemCode\\.0" ).html(str);
		  function printOnly(){
				CreateInvoiceUtil.printPage();
		  }                 
		function billTypeChange(){
			var s = $('#invoiceType').val();
			if(s == "cash"){
				$('#labour_row').hide();
				$('#subtotal_row').hide();
				$('#labourText').val(0);
			}else{
				$('#labour_row').show();
				$('#subtotal_row').show();
			}
			var e = $('#labourLabel').val();
			CreateInvoiceUtil.calculateInvTaxAndTotal(e);
		}
		
		var cutomerList = new Array();
		for(var i = 0; i< customerDetails.length; i++){
			var name = customerDetails[i]["name"];
			if(cutomerList.indexOf(name )== -1){
				
			cutomerList.push(customerDetails[i]["name"]);
			}
		}
		console.log(cutomerList);
		$('#billingAddress1').autocomplete({source : cutomerList });
		// $( "#invoiceDate" ).datepicker({ dateFormat: 'dd-mm-yy' });
		function showCustomerAddress(){
			var customer =  $('#billingAddress1').val();
			for(var i = 0; i< customerDetails.length; i++){
				if(customerDetails[i]["name"] == customer){
					$('#billingAddress2').val(customerDetails[i]["address"]);
					
				}
			}
		}
		var ids = ["invoiceTextBox","invoiceDateLabel","invoiceDate","invoiceTypeLabel","invoiceType","billToLabel","billingAddress1","billingAddress2","itemCode.1","itemDesc.1","itemQty.1","itemRate.1","labourText"];
		function tempFixOfIndex(ele){
			if(ele.id == 'itemRate.1'){
				if(document.getElementById('itemCode.2')){
					document.getElementById("itemCode.2").focus();
				}
			}
		}
		function getTemplateContent(){
			

			 $.ajax({
			url : "invoicetemplate.do?operation=get",
			method : "GET",
			datatype : "Json",
			success : function(response) {
				var part = $('#part').val();
				var header = response["header"];
				var footer = response["footer"];
				$('#header').html(header);
				$('#footer').html(footer);
			},
			error : function() {
			}
		});
	}
		getTemplateContent();
		
	</script>



 <script type="text/javascript" src="ig/free-invoice.js"></script> 

</body>
</html>