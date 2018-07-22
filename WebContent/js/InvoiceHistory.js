var groups = new Array();
function drawGrid() {
	jQuery("#loaderr").jqGrid('GridUnload');
	jQuery("#loaderr").jqGrid(
			{
				url : 'invoice.do?requestType=getInvoiceHistroy',
				datatype : "json",
				colNames : ['Invoice Number', 'Customer Name', 'Invoice Date', 'Invoice Type','Due Date', 'Items','Invoice Amount','Tax Amount'  ],
				colModel : [
				 {name:'invoiceno',index:'invoiceno', width:55,editable : true,key: true},
				 {
					name : 'customername',
					index : 'customername',
					editable : true
				}, {
					name : 'invoicedate',
					index : 'invoicedate',
					editable : true,
					editoptions:{onkeypress:"return numeralsOnly(event)"}
				}, {
					name : 'invoicetype',
					index : 'invoicetype',
					editable : true,
				},{
					name : 'duedate',
					index : 'duedate',
					editable : true
				}, {
					name : 'totalitem',
					index : 'totalitem',
					editable : true
				}, {
					name : 'total',
					index : 'total',
					editable : true
				}, {
					name : 'taxamount',
					index : 'taxamount',
					editable : true
				} ],
				rowNum : 50,
				height:'100%',
				rowList : [ 50,100,200,300 ],
				pager : '#ploaderr',
				sortname : 'currentbalance',
				autowidth:true,
				viewrecords : true,
				 multipleSearch:true,
				sortorder : "desc",
				editurl : "invoice.do?requestType=editaccounts",
				caption : "Invoices ",
				subGrid: true,
				loadonce: true,
				multipleGroup:true,
				grouping: true,
				//subGridUrl : "invoice.do?requestType=invoiceitems&invoiceId=",
				 subGridOptions: { 
		                "plusicon" : "ui-icon-triangle-1-e", 
		                "minusicon" : "ui-icon-triangle-1-s", 
		                "openicon" : "ui-icon-arrowreturn-1-e",
		                "reloadOnExpand" : true
		            }, 
		            subGridRowExpanded: function(subgrid_id, row_id) { 
		                var subgrid_table_id, pager_id; 
		                subgrid_table_id = subgrid_id+"_t"; 
		                pager_id = "p_"+subgrid_table_id; 
		                $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>"); 
		                console.log(subgrid_table_id);
		                jQuery("#"+subgrid_table_id).jqGrid({ 
		                    url:"invoice.do?requestType=invoiceitems&id="+row_id, 
		                    datatype: "json", 
		                    mtype: 'POST',
		                    colNames:['Item Code','Item Name','Item Quantity', 'Item Rate','Item Discount','Total Tax', 'Total Amount'],
		                    colModel: [ 
		                        {name:'code',index:'code', width:170}, 
		                        {name:'itemName',index:'itemName', width:170},
		                        {name:'itemQty',index:'itemQty', width:170},
		                        {name:'itemRate',index:'itemRate', width:100},
		                        {name:'itemDiscount',index:'itemDiscount', width:100},
		                        {name:'itemTax',index:'itemTax', width:100},
		                        {name:'itemTotal',index:'itemTotal', width:80, align:"right"},
		                        
		                    ], 
		                    rowNum:8, 
		                    pager: pager_id, 
		                    sortname: 'itemName', 
		                    sortorder: "asc",
		                    viewrecords: true, 
		                    sortable: true,
		                    height: '100%' ,
		                    autowidth: true,

		                }); 
		           // jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:true,del:false}); 
		            }
				
			});
	jQuery("#loaderr").jqGrid('filterToolbar',{searchOperators : true});
	jQuery("#loaderr").jqGrid('navGrid', "#ploaderr", {
		edit : false,
		add : false,
		del : false
	});
//	jQuery("#loaderr").jqGrid('inlineNav', "#ploaderr");
}

function ready() {
	drawGrid();
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
