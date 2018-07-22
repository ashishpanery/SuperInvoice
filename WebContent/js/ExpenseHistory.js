var groups = new Array();
function drawBarChart(responseData){
	console.log(responseData);
	$('#bar_container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Sales Report: '+$('#reportType').val().toUpperCase()
        },
       
        xAxis: {
            categories: responseData["timeseries"]
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Amount (INR)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Income',
            data: responseData["incomeData"]["data"],
            color : '#FF9933'

        }, {
            name: 'Expense',
            data: responseData["expenseData"]["data"],
            color : '#6666FF'

        }]
    });
	Highcharts.setOptions({
	    colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
	});

}
function drawPieChart(responseData){
	  $('#pie_container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: 'Category Wise Expenses'
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Browser share',
	            data: responseData["data"]
	        }]
	    });
	}
	
function changeChartDuration(){
	callForBarData();
	callForPieData();
}

function callForPieData(){
	var reportType = $('#reportType').val();
	$.ajax({
		type: "GET",
		url: "expenses.do?requestType=getExpensePieData&reportType="+reportType,
		success : function(response){
			drawPieChart(response);
		},
		error: function(){						
			alert('Error while request for Bar Chart Data..');
		}
	});
}

function callForBarData(){
	var reportType = $('#reportType').val();
	$.ajax({
		type: "GET",
		url: "expenses.do?requestType=getExpenseBarData&reportType="+reportType,
		success : function(response){
			drawBarChart(response);
		},
		error: function(){						
			alert('Error while request for Pie Chart Data..');
		}
	});
}



function callForDueRecords(){
	
	var reportType = $('#reportType').val();
	$.ajax({
		
		type: "GET",
		url: "expenses.do?requestType=getExpenseDueData&reportType="+reportType,
		datatype: "Json",
				success : function(response) {

					var arr = response["data"];
					var trHTML = '<tr><td>Item Name</td><td>Current Qty</td><td>Re Order Qty</td></tr>';
					for ( var i = 0; i < arr.length; i++) {
						var obj = arr[i];
						trHTML += '<tr><td>' + obj["itemname"] + '</td><td>'
								+ obj["qty"] + '</td><td>' + obj["reorder_qty"]
								+ '</td></tr>';
					}
					
					$('#mytable').append(trHTML);
					if(arr.length == 0){
						$('#outside').hide();
					}else{
						setTimeout(function() {
							$('#outside').slideDown(1000);
						}, 1000);
						
					}

				},
		error: function(){						
			alert('Error ');
		}
	});
}


function drawGrid() {
	jQuery("#loaderr").jqGrid('GridUnload');
	jQuery("#loaderr").jqGrid(
			{
				url : 'expenses.do?requestType=getExpenseHistory',
				datatype : "json",
				colNames : ['expenseId','Category', 'Date', 'Submitted By', 'Amount','Description','Year','Month'],
				colModel : [
					{
						name : 'expenseId',
						index : 'expenseId',
						editable : true,
						key:true
					},        
				 {
					name : 'category',
					index : 'category',
					editable : true
				}, {
					name : 'expense_date',
					index : 'expense_date',
					editable : true,
					editoptions:{onkeypress:"return numeralsOnly(event)"}
				}, {
					name : 'submitted_by',
					index : 'submitted_by',
					editable : true
				},{
					name : 'amount',
					index : 'amount',
					editable : true,
					sorttype:"float",
					formatter:"number",
					summaryType:'sum',
					summaryTpl:'<b>Total: {0}</b>'
				}, {
					name : 'description',
					index : 'description',
					editable : true
				}, {
					name : 'year',
					index : 'year',
					editable : true
				}, {
					name : 'month',
					index : 'month',
					editable : true
				}],
				rowNum : 50,
				height:'100%',
				rowList : [ 50,100,200,300 ],
				pager : '#ploaderr',
				sortname : 'currentbalance',
				autowidth:true,
				viewrecords : true,
				multipleSearch:true,
				sortorder : "desc",
				editurl : "expenses.do?requestType=editexpenses",
				caption : "Expenses ",
				loadonce: true,
				multipleGroup:true,
				grouping: true
				
			});
	jQuery("#loaderr").jqGrid('filterToolbar',{searchOperators : true});
	  $("#dynamicGrouping").change(function () {
          var groupingName = $(this).val();
		      if (groupingName != "clear") {
        	  jQuery("#loaderr").jqGrid('groupingGroupBy', groupingName, {

                  groupOrder : ['desc'],
                  groupCollapse: false,
                  groupSummary : [true] 
              });
 
          } else {
        	  jQuery("#loaderr").jqGrid('groupingRemove');
          }
      });
	jQuery("#loaderr").jqGrid('navGrid', "#ploaderr", {
		edit : true,
		add : false,
		del : true
	});
}

function ready() {
	drawGrid();
	changeChartDuration();
	callForDueRecords();
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
