var allTaxes = new Object();
allTaxes = {"14.0":"14.0","5.0":"5.0"};
function getAllTaxes(){

	$.ajax({
		  url: "tax.do?requestType=getAllTaxes",
		  cache: false
		})
		  .done(function( data ) {
			  console.log(data);
			  allTaxes = data;
		  });
}
function drawJqGrid()
{
	getAllTaxes();
	

//		var taxes = $.ajax({
//		url : 'tax.do?requestType=getAllTaxes',
//		async : false,
//		success : function(data, result) {
//			if (!result)
//				alert('Failure to retrieve the Countries.');
//		}
//	}).responseText;
	
	jQuery("#loaderr").jqGrid('GridUnload');
	  jQuery("#loaderr").jqGrid({
	  	url:'ItemDetail.do?requestType=getHistory&q=1',
	   	datatype: "json",
	   	colNames:['','Item Name','Item Qty','Unit of Measurment','Tax','Item Re Order Qty','Item Purchase Price', 'Item Sales Price Cash', 'Item Sales Price Credit','Supplier ','Date','Bill No.','Item Code','Operation'],
	   	colModel:[
	   	          {name:'checkbox',index:'checkbox',width:'20px',align:"center",editable : false},
	
	   		{name:'item_desc',index:'item_desc',width:'100px',align:"center",editable : true},
	   		{name:'qty',index:'qty',align:"center",editable : true,editoptions:{onkeypress:"return numeralsOnly(event)"}},
	   		{name:'unit_of_measurement',index:'unit_of_measurement',  align:"center",width:'180px',editable : true,edittype:'select', editoptions:{value:{Meter:'Meter',Number:'Number'}}},
	   		{name:'applicable_tax',index:'applicable_tax',  align:"center",width:'180px',editable : true,edittype:'select'},
	   		{name:'item_reorder_qty',index:'item_reorder_qty',align:"center",editable : true,editoptions:{onkeypress:"return numeralsOnly(event)"}},
	   		{name:'item_retail_price',index:'item_retail_price',  align:"center",width:'150px',editable : true,editoptions:{onkeypress:"return numeralsOnly(event)"}},
	   		{name:'item_sales_price_cash',index:'item_sales_price_cash',  align:"center",width:'150px',editable : true,editoptions:{onkeypress:"return numeralsOnly(event)"}},
	   		{name:'item_sales_price_credit',index:'item_sales_price_credit',  align:"center",width:'150px',editable : true,editoptions:{onkeypress:"return numeralsOnly(event)"}},
		   	
	   		{name:'supplier',index:'supplier',  align:"center",width:'80px',editable : true,dataInit:function(el){ 
            	
                $(el).autocomplete({source:["Ashish","Ram Das"]}); 
          }},
	   		{name:'date', index:'date', width:90, editable:true, editoptions:{size:20, 
                dataInit:function(el){ 
                	
                      $(el).datepicker({dateFormat:'dd-mm-yy'}); 
                }, 
                defaultValue: function(){ 
                  var currentTime = new Date(); 
                  var month = parseInt(currentTime.getMonth() + 1); 
                  month = month <= 9 ? "0"+month : month; 
                  var day = currentTime.getDate(); 
                  day = day <= 9 ? "0"+day : day; 
                  var year = currentTime.getFullYear(); 
                  return day+"-"+month+"-"+year; 
                } 
              } 
            },
	   		
	   		{name:'billno',index:'billno',  align:"center",width:'80px',editable : true},
	   		{name:'itemcode',index:'itemcode',width:'100px',align:"center",editable : false,key: true,hidden:true},
	   		{name:'oper',index:'oper',  align:"center",width:'100px',editable : false,hidden:true}
	   	 
	   	],
	   	rowNum:50,
	   	width: '100%',
        height: 'auto',
	   	rowList:[50,100,200,300],
	   	pager: '#ploaderr',
	   	autowidth:true,
		viewrecords : true,
	    sortorder: "asc",
	    multipleSearch:true,
	    loadonce: true,
	    reloadAfterSubmit:true,
	    multipleGroup:true,
		grouping: true,
		reload:true,
	    editurl : "ItemDetail.do?requestType=edit",
	    caption : "Item Inventory",
	    loadComplete: function() {
	    	$('#loaderr').setColProp('applicable_tax', { editoptions: { value: allTaxes} });
	    	}
	});
	  
	  jQuery("#loaderr").jqGrid('filterToolbar',{searchOperators : true});
	  jQuery("#loaderr").jqGrid('navGrid', "#ploaderr", {
			edit : true,
			add : true,
			del : true
		},{reloadAfterSubmit: true,
			afterSubmit:function(response,postdata)
			 {
			 $("#loaderr").jqGrid('setGridParam',{datatype:'json'}).trigger('reloadGrid');
			 return [true,"",''];
			 },
		}, {reloadAfterSubmit: true,
				 afterSubmit:function(response,postdata)
				 {
				 $("#loaderr").jqGrid('setGridParam',{datatype:'json'}).trigger('reloadGrid');
				 return [true,"",''];
				 }
				
			 },{reloadAfterSubmit: true,
				 afterSubmit:function(response,postdata)
				 {
				 $("#loaderr").jqGrid('setGridParam',{datatype:'json'}).trigger('reloadGrid');
				 return [true,"",''];
				 }
		});
	  $.extend(true, $.jgrid.edit, {
          onInitializeForm: function ($form) {
              $form.css({height: "100%"});
              $form.closest(".ui-jqdialog").css({height: "auto"});
          }
      });
	
}
function printAllBarCode(){
	
	window.open("barcodegenerator.jsp?printtype=multiple&data="+JSON.stringify(selectedItem));
}
function selectItem(e,itemPrice){
	var code = e.id;
	selectedItem[code] = itemPrice;
}
function showItemDiv(haveToShow){
	
	if(haveToShow){
		$('#inventory_table').show();
		$('#barcode_table').hide();
	}else{
		$('#inventory_table').hide();
		$('#barcode_table').show();
		
	}
}
function printBarCode (code,rate){
	console.log(code);
	
	$('#barcode_string').val(code);
	$('#barcode_rate').val(rate);
	
	showItemDiv(false);
//	window.open("barcode.jsp?code="+code+"&rate="+rate);
}
function printBarCodeMatrix(){
	
	var code = $('#barcode_string').val();
	var rate = $('#barcode_rate').val();
	var width = $('#barcode_width').val();
	var height = $('#barcode_height').val();
	var cols = $('#barcode_column').val();
	var rows = $('#barcode_rows').val();
	
	var param = "code="+code+"&rate="+rate+"&width="+width+"&height="+height+"&cols="+cols+"&rows="+rows;
	console.log(param);
	window.open("barcode.jsp?"+param);
}
function printChequeById(type,chequeId){
	var chequeno = 0;
	if(type=="cheque"){
		 chequeno = prompt("Please enter 6 digit chque number");
	}
	$.ajax({
		url : 'ChequeDetail.do?requestType=print&printType='+type+'&chequeId='+chequeId+'&chequeno='+chequeno+'&printer='+$('#chequeprinter').val(),
		type:'POST',
		dataType: "json",
		
	}).done(
			function(data){
				
				
				}
	);
	
}
function numeralsOnly(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : 
        ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46)) {
        return false;
    }
    return true;
}
function exportData(type){
	$.ajax({
		url : 'ChequeDetail.do?requestType=export',
		type:'POST',
		dataType: "json",
		
	}).done(
			function(data){
				window.open("http://localhost:8787/CheqePrintService/report/"+data["path"]+".csv");
				}
	);
	
}