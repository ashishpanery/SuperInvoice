


function showItemDetailByCode(element,isKeyPress){
	var code = element.value;
	if(code == "" || code == undefined ){
		return;
	}
	var index = element.id;
	index = index.substring(index.indexOf('.'));
	var flag = true;
	for(var i = 0; i< itemDetail.length;i++){
		if(itemDetail[i]["itemcode"] == code || itemDetail[i]["item_desc"] == code){
			document.getElementById("itemDesc"+index).value = itemDetail[i]["item_desc"];
			document.getElementById("itemPurchase"+index).value = itemDetail[i]["item_retail_price"];
			document.getElementById("itemQty"+index).value = 1;
			document.getElementById("taxid"+index).value = itemDetail[i]["applicable_tax"];
			console.log("Rate is set for taxid"+index+ " - "+itemDetail[i]["applicable_tax"]);
			if(itemDetail[i]["qty"] == 0){
				//alert("Item is out of stock.");
				document.getElementById("itemQty"+index).value = 0;
			}
			var key = "item_sales_price";
			if($('#invoiceType').val() == "credit"){
				key =  "item_sales_price_credit";
			}
			
			document.getElementById("itemRate"+index).value = itemDetail[i][key];
			flag = false;
		}
	}
	if(flag && !isKeyPress){
		//alert("Item is not in stock with code-"+code);
	}
	console.log(($(element).hasClass("lastLineItem") && $(element).val().length != 0));
	if(!isKeyPress){
		
	
	if ($(element).hasClass("lastLineItem") && $(element).val().length != 0) {
		
		var n = $(element).attr("id").lastIndexOf(".");
		var r = Number($(element).attr("id").substring(n + 1)) + Number(1);
		CreateInvoiceUtil.addInvoiceLineItem();
		$(element).removeClass("lastLineItem");
		$("#itemCode\\." + r).addClass("lastLineItem");
		//$("#itemCode\\." + r).focus();   
	}
	}
	CreateInvoiceUtil.calculateItemTotal(document.getElementById("itemQty"+index));
	
}

var groups = new Array();

function getItemDetails(){
	
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
var CreateInvoiceUtil = function() {
};
CreateInvoiceUtil.TOTAL_LINE_ITEMS = 1;
CreateInvoiceUtil.addInvoiceLineItem = function() {
	var e = CreateInvoiceUtil.TOTAL_LINE_ITEMS + 1, t = "lineItem." + e, n = "itemClose."
			+ e;
	var r = $(".trClone").clone();
	r.attr("id", t).removeClass("trClone hide").addClass("row-item");
	CreateInvoiceUtil.assignIdToChildElements(r, e);
	r.appendTo(".lineItems");
	CreateInvoiceUtil.updateTabIndexForLabels(e);
	CreateInvoiceUtil.TOTAL_LINE_ITEMS = e
};
CreateInvoiceUtil.showCloseIcon = function(e, t) {
	var n = $(e).find(".closeicon");
	if (t) {
		n.removeClass("hide").addClass("show");
		return
	}
	n.removeClass("show").addClass("hide");
	return
};
CreateInvoiceUtil.removeLineItem = function(e) {
	var t = $(e).attr("id").lastIndexOf(".");
	var n = $(e).attr("id").substring(t + 1);
	var r = $(".lineItems").children().length - 1;
	if (r > 1) {
		$("#lineItem\\." + n).remove();
		r--
	}
	CreateInvoiceUtil.calculateInvoiceTotal()
};
CreateInvoiceUtil.assignIdToChildElements = function(e, t) {
	var n = $(e).children(), r = n.length, i = $("#itemRate\\." + (t - 1))
			.attr("tabindex") + 1;
	for ( var s = 0; s < r; s++) {
		CreateInvoiceUtil.assignIdToChildElements(n[s], t);
		var o = $(n[s]).prop("tagName");
		if (o === "INPUT" || o === "SPAN" || o === "TEXTAREA" || o === "A"|| o === "SELECT") {
			if ($(n[s]).attr("id") === undefined) {
				continue
			}
			var u = $(n[s]).attr("id").lastIndexOf("."), a = $(n[s]).attr("id")
					.substring(0, u);
			elementId = a + "." + t;
			$(n[s]).attr("id", elementId);
			if (o === "TEXTAREA") {
				$(n[s]).autogrow();
				$(n[s]).autocomplete({
                    source: itemName
                });
				$(n[s]).addClass("lastLineItem")
			}else if(o ==="INPUT" && elementId.indexOf("itemCode") != -1){
				$(n[s]).autocomplete({
                    source: itemCodeList
                });
			}
			if ($(n[s]).attr("tabindex") !== undefined) {
				$(n[s]).attr("tabindex", i)
			}
		}
	}
};
CreateInvoiceUtil.updateTabIndexForLabels = function(e) {
	var t = $("#itemRate\\." + e).attr("tabindex") + 1, n = [ "subTotalLabel",
			"taxLabel", "totalLabel", "currencyCode", "notesLabel",
			"customerNotes", "termsLabel", "terms" ];
	for ( var r = 0; r <= 7; r++) {
		$("#" + n[r]).attr("tabindex", t);
		t++
	}
};
CreateInvoiceUtil.calculateItemTotal = function(e) {
	var t = $(e).attr("id").lastIndexOf("."), n = $(e).attr("id").substring(
			t + 1), r = "0.00";
	if (isNaN($("#itemQty\\." + n).val())) {
		$("#itemQty\\." + n).val("1.00")
	} else if (isNaN($("#itemRate\\." + n).val())) {
		$("#itemRate\\." + n).val("0.00")
	}
	var i = Number($("#itemQty\\." + n).val()), s = Number($("#itemRate\\." + n)
			.val());
	r = (i * s).toFixed("2");
	$("#itemTotal\\." + n).html(r);
	var l = Number(n) + 1;
	var taxId = $('#taxid\\.'+n).val();
	var taxRate = parseFloat(taxDetail[taxId]["rate"]);
	var taxVal = ((r*taxRate)/100).toFixed("2");
	console.log("taxVal"+taxVal);
	$('#taxvalue\\.'+n).val(taxVal);
	CreateInvoiceUtil.calculateInvoiceTotal();
	$('#itemCode\\.'+l).focus();
	return
};
CreateInvoiceUtil.calculateInvoiceTotal = function() {
	var e = Number(0), t = "0.00", n = "";
	var taxes = new Object();
	for ( var r = 1; r <= CreateInvoiceUtil.TOTAL_LINE_ITEMS; r++) {
		n = $("#itemTotal\\." + r);
		if (n === null || isNaN(n.html())) {
			continue;
		}
		t = Number(n.html());
		e = e + t;
		var taxKey = $("#taxid\\." + r).val();
		var val =  parseFloat($("#taxvalue\\." + r).val());
		if(taxes.hasOwnProperty(taxKey)){
			val = parseFloat(taxes[taxKey]) + val;
		}
		taxes[taxKey] = val;
	}
	e = e.toFixed("2");
	$("#subTotal").html(e);
	console.log(taxes);
	CreateInvoiceUtil.calculateInvTaxAndTotal();
	
};
CreateInvoiceUtil.hideHelpText = function() {
};
CreateInvoiceUtil.checkAndAddNewLineItem = function(e, t) {
	if (t.keyCode === 9) {
		return
	}
	if ($(e).hasClass("lastLineItem") && $(e).val().length === 0) {
		var n = $(e).attr("id").lastIndexOf(".");
		var r = Number($(e).attr("id").substring(n + 1)) + Number(1);
		CreateInvoiceUtil.addInvoiceLineItem();
		$(e).removeClass("lastLineItem");
		$("#itemDesc\\." + r).addClass("lastLineItem")
	}
};
CreateInvoiceUtil.calculateInvTaxAndTotal = function() {
	var e = $("#taxLabel").val(), t = e.match(/[\d\.]+/g), n = $("#subTotal")
			.html(), r = t !== null ? t[0] / 100 : 0, i = r * n, s = i === 0 ? ""
			: i.toFixed("2");
	$("#taxAmt").html(s);
	var o = Number(n) + Number(i);
	o = o.toFixed("2");
	$("#total").html(o)
};
CreateInvoiceUtil.printPage = function() {
	var e = $(".lineItems").children().length - 1;
	for ( var t = 1; t <= e; t++) {
		var n = $("#itemDesc\\." + t).val();
		if (n === "" || n === undefined) {
			$("#lineItem\\." + t).addClass("hide")
		}
	}
	var r = $("#attID").val();
	if (r === "") {
		$("#attID").addClass("hide")
	}
	window.print();
};
CreateInvoiceUtil.showHelpText = function(e) {
	$("#helpText").removeClass("hide");
	$("#helpTxtRHS").addClass("hide");
	var t = "160", n = "helpText";
	if (e === 1) {
		t = $("#billToLabel").offset().top
	} else if (e === 2) {
		t = $("#billToLabel").offset().top
	} else if (e === 3) {
		t = $("#itemDesc\\.1").offset().top
	} else if (e === 4) {
		t = $("#taxLabel").offset().top
	} else if (e === 5) {
		t = $("#terms").offset().top
	} else if (e === 7) {
		t = $("#customerNotes").offset().top
	}
	CreateInvoiceUtil.showHelpContent(n, t, e)
};
CreateInvoiceUtil.showHelpContent = function(e, t, n) {
	var r = t + "px", i = "helpcontent", s = "helpheader";
	var o = [ "Your Address", "Client's Address", "Invoice Details",
			"Item & Rate", "What's the Tax", "Terms of Payment",
			"Document Title", "Special Note", "It's your invoice!" ];
	var u = [
			"Hint: Address that appears on your credit card statement.",
			"Hint: Address where you'd like to send the invoice.",
			"Number your invoices the way you want. Remember to add the payment due date as well.",
			"Specify the products or services sold. What, how many and how much... everything.",
			"Customize to add any tax. Just need to enter the tax %",
			"Very important section! Tell your customer by when you'll like to get paid.",
			"Call it as you wish-Invoice or Estimate or Purchase Order (PO).",
			"Have anything specific to tell your customer? Use this space",
			"You can edit every word on this online invoice.",
			"Edit fields and put your own values." ];
	$("#" + s).html(o[n]);
	$("#" + i).html(u[n]);
	$("#" + e).css("top", r)
};
CreateInvoiceUtil.updateInvoiceSubject = function(e) {
	var t = $(e).val();
	if (t === "") {
		$("#attID").attr("value", "");
		$("#attID").attr("placeholder", "Dear Client");
		return
	}
	var n = "Dear  " + t + ",";
	$("#attID").attr("value", n)
}
