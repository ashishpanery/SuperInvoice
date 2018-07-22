var groups = new Array();
function drawGrid() {
	jQuery("#loaderr").jqGrid('GridUnload');
	jQuery("#loaderr").jqGrid(
			{
				url : 'user.do?requestType=getAllUsrs',
				datatype : "json",
				colNames : ['Login Id', 'Password', 'Role', 'User Name'  ],
				colModel : [
				 {name:'loginid',index:'loginid', width:55,editable : false,key: true},
				 {
					name : 'password',
					index : 'password',
					editable : true
				}, {
					name : 'role',
					index : 'role',
					editable : true,
				}, {
					name : 'username',
					index : 'username',
					editable : true,
				} ],
				rowNum : 50,
				height:'100%',
				rowList : [ 50,100,200,300 ],
				pager : '#ploaderr',
				sortname : 'loginid',
				autowidth:true,
				viewrecords : true,
				 multipleSearch:false,
				sortorder : "desc",
				editurl : "user.do?requestType=edituser",
				caption : "User ",
				subGrid: false,
				loadonce: true,
				multipleGroup:true,
				grouping: true,
				reloadAfterSubmit:true,
				reload:true
				
				
			});
	jQuery("#loaderr").jqGrid('filterToolbar',{searchOperators : false});
	jQuery("#loaderr").jqGrid('navGrid', "#ploaderr", {
		edit : true,
		add : false,
		del : false
	});
//	jQuery("#loaderr").jqGrid('inlineNav', "#ploaderr");
}

function drawTaxGrid() {
	jQuery("#loaderr_tax").jqGrid('GridUnload');
	jQuery("#loaderr_tax").jqGrid(
			{
				url : 'tax.do?requestType=getAllTax',
				datatype : "json",
				colNames : ['Id', 'Tax Name', 'Value'],
				colModel : [
				 {name:'taxid',index:'taxid', width:55,editable : false,key: true,hidden:true},
				 {
					name : 'taxname',
					index : 'taxname',
					editable : true
				}, {
					name : 'taxvalue',
					index : 'taxvalue',
					editable : true,
				} ],
				rowNum : 50,
				height:'100%',
				rowList : [ 50,100,200,300 ],
				pager : '#ploaderr_tax',
				sortname : 'taxname',
				autowidth:true,
				viewrecords : true,
				 multipleSearch:false,
				sortorder : "desc",
				editurl : "tax.do?requestType=edittax",
				caption : "Tax Type ",
				subGrid: false,
				loadonce: true,
				multipleGroup:true,
				grouping: true,
				reloadAfterSubmit:true,
				reload:true
				
				
			});
	jQuery("#loaderr_tax").jqGrid('filterToolbar',{searchOperators : false});
	jQuery("#loaderr_tax").jqGrid('navGrid', "#ploaderr_tax", {
		edit : true,
		add : true,
		del : true
	});

}
function ready() {
	drawGrid();
	drawTaxGrid();
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
