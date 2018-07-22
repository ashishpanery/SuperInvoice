function setSerialNumber(){
	console.log("In Set Serial Number");
	var i = 0;
	var $text = $('.Itemserial-col input:text');
	$text.each(function () {
		if(!(this.id == "itemSerial")){
			this.value = i;
			i++;
		}
	});
}

function saveandPrintInvoice(){
			var invoiceNo = $("#invNumber").val();
			var invoiceDate = $('#invoiceDate').val();
			var dueDate = $('#dueDate').val();
			
			var customerName = $('#billingAddress1').val();
			var customerAddress = $('#billingAddress2').val();
			var customerAddressCity = $('#billingAddress3').val();
			var customerAddressCountry = $('#billingAddress5').val();
			
			var totalItem = CreateInvoiceUtil.TOTAL_LINE_ITEMS;
			
			
			if(invoiceNo == ""){
				alert("invoiceNo can't be empty. Please refresh the page to generate new Invoice Number.");
				return;
			}
			if(customerName == ""){
				alert("Customer Name can't be empty.");
				$('#billingAddress1').focus();
				return;
			}
			
			
			console.log("Total Item :"+totalItem);
			
			
			var e = CreateInvoiceUtil.TOTAL_LINE_ITEMS + 1, t = "lineItem." + e, n = "itemClose."
				+ e;

			var count = parseInt(e);
			var itemArray = new Array();
			for(var i=1;i<count;i++){
				var itemDetail = new Object();
				var code = $('#itemCode\\.'+i).val();
				var itemName = $('#itemDesc\\.'+i).val()
				console.log("code  : ="+code);
				if( (code == undefined || code == "New Item") && (itemName == undefined || itemName == "") ){
					$("#lineItem\\."+i).hide();
					continue;
				}
				itemDetail["code"]=code;
				var itemName = $('#itemDesc\\.'+i).val();
				itemDetail["itemName"]= itemName.toLowerCase();
				itemDetail["itemQty"]=$('#itemQty\\.'+i).val();
				itemDetail["itemRate"]=$('#itemRate\\.'+i).val();
				itemDetail["itemTotal"]=$('#itemTotal\\.'+i).text();
				itemDetail["itemDiscount"]=$('#itemDiscount\\.'+i).val();
				itemDetail["itemTax"]=$('#taxvalue\\.'+i).val();
				itemArray.push(itemDetail);
			}
			if(itemArray.length == 0){
				alert("No Item is added in invoice. Please add a item before generating invoice.");
				return;
			}
			var subTotal = $('#subTotal').text();
			var taxAmt = $('#taxAmt').text();
			var total = $('#total').text();
			
		
			
			var obj = new Object();
			obj["invoiceNo"] = invoiceNo;
			obj["invoiceDate"] = invoiceDate;
			obj["dueDate"] = dueDate;
			obj["customerName"] = customerName;
			obj["customerAddress"] = customerAddress;
			obj["customerAddressCity"] = customerAddressCity;
			obj["customerAddressCountry"] = customerAddressCountry;
			obj["totalItem"] = ""+ totalItem;
			obj["itemArray"] = itemArray;
			obj["subTotal"] = subTotal;
			obj["taxAmt"] = taxAmt;
			obj["total"] = total;
			obj["invoicetype"] = $('#invoiceType').val();
			
			console.log(obj);
			console.log(JSON.stringify(obj));
//			$('.Itemcode-col').hide();
			
			$.ajax({
				url : 'invoice.do?requestType=saveInvoice&obj='+encodeURIComponent(JSON.stringify(obj)),
//				url : 'invoice.do?requestType=saveInvoice',
				type:'POST',
				dataType: "json",
//				data: {obj: obj}
			}).done(
					function(data){
						location.reload();
						
						}
			);
			CreateInvoiceUtil.printPage();
			
		}
var CreateInvoiceUtil = function() {
};
CreateInvoiceUtil.TOTAL_LINE_ITEMS = 1;
CreateInvoiceUtil.addInvoiceLineItem = function() {
	var e = CreateInvoiceUtil.TOTAL_LINE_ITEMS + 1, t = "lineItem." + e, n = "itemClose."
			+ e;
	var r = $(".trClone").clone(true);
	r.attr("id", t).removeClass("trClone hide").addClass("row-item");
	CreateInvoiceUtil.assignIdToChildElements(r, e);
	r.appendTo(".lineItems");
	CreateInvoiceUtil.updateTabIndexForLabels(e);
	CreateInvoiceUtil.TOTAL_LINE_ITEMS = e;
	setSerialNumber();
	
	
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
	if (r > 0) {
		$("#lineItem\\." + n).remove();
		r--;
	}
	CreateInvoiceUtil.calculateInvoiceTotal();
	setSerialNumber();
	
};
CreateInvoiceUtil.assignIdToChildElements = function(e, t) {
	var n = $(e).children(), r = n.length, i = $("#itemRate\\." + (t - 1))
			.attr("tabindex") + 1;
	for ( var s = 0; s < r; s++) {
		CreateInvoiceUtil.assignIdToChildElements(n[s], t);
		var o = $(n[s]).prop("tagName");
		if (o === "INPUT" || o === "SPAN" || o === "TEXTAREA" || o === "A" || o === "SELECT") {
			if ($(n[s]).attr("id") === undefined) {
				continue
			}
			var u = $(n[s]).attr("id").lastIndexOf("."), a = $(n[s]).attr("id")
					.substring(0, u);
			elementId = a + "." + t;
			$(n[s]).attr("id", elementId);
			if (o === "TEXTAREA") {
//				$(n[s]).autogrow();
				$(n[s]).addClass("lastLineItem")
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
			.val()), d = Number($("#itemDiscount\\." + n).val());
	r = (i * s).toFixed("2");
	dc = (r*d)/100; 	
	r = (r-dc).toFixed("2");
	$("#itemTotal\\." + n).html(r);
	var taxId = $('#taxid\\.'+n).val();
	var taxRate = parseFloat(taxDetail[taxId]["rate"]);
	var taxVal = ((r*taxRate)/100).toFixed("2");
	console.log("taxVal"+taxVal);
	$('#taxvalue\\.'+n).val(taxVal);
	CreateInvoiceUtil.calculateInvoiceTotal();
	return
};
CreateInvoiceUtil.calculateInvoiceTotal = function() {
	var e = Number(0), t = "0.00", n = "";
	var taxes = new Object();
	
	for ( var r = 1; r <= CreateInvoiceUtil.TOTAL_LINE_ITEMS; r++) {
		n = $("#itemTotal\\." + r);
		if (n === null || isNaN(n.html())) {
			continue
		}
		t = Number(n.html());
		e = e + t;
		var taxKey = $("#taxid\\." + r).val();
		var val =  parseFloat($("#taxvalue\\." + r).val());
		if(taxes.hasOwnProperty(taxKey)){
			val = parseFloat(taxes[taxKey]) + val;
		}
		if(taxKey != undefined && taxKey != "")
		taxes[taxKey] = val.toFixed(2);
	}
	e = e.toFixed("2");
	$("#subTotal").html(e);
	var taxTable = '';
	console.log(taxes);
	appliedTaxes = taxes;
	for(var tax in taxes){
		console.log(tax);
		var taxName = taxDetail[tax]["name"]+"("+taxDetail[tax]["rate"]+"%)";
		taxTable += '<tr><td style="width:58%;"><input type="text" id="'+tax+'" value="'+taxName+'" ></td>'+
		'<td><input type="text" id="'+tax+'_val" value="'+taxes[tax]+'"></td></tr>';
	}
	taxTable += '<tr><td><input type="text" id="round_off" value="Round Off" style=""></td>'+
	'<td><input type="text" id="round_off_val" value=""></td></tr>';
	$('#tax_table').html(taxTable);
	CreateInvoiceUtil.calculateInvTaxAndTotal()
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
	setSerialNumber();
};
CreateInvoiceUtil.calculateInvTaxAndTotal = function() {
	var e = $("#taxLabel").val(), t = e.match(/[\d\.]+/g), n = $("#subTotal")
			.html(), r = t !== null ? t[0] / 100 : 0, i = r * n, s = i === 0 ? ""
			: i.toFixed("2");
	$("#taxAmt").html(s);
	var o = Number(n) + Number(i);
	o = o.toFixed("2");
	var l = $('#labourText').val();
	l = Number(l);
	o = Number(o) +Number(l);
	console.log("appliedTaxes :"+appliedTaxes);
	var totalTax = 0;
	for(var tax in appliedTaxes){
		if(tax != undefined && tax != ""){
			totalTax += parseFloat(appliedTaxes[tax]);
		}
	}
	o = o+totalTax;
	$("#total").html(Math.ceil(o));
	console.log((Math.ceil(o)-o).toFixed(2));
	$('#round_off_val').val((Math.ceil(o)-o).toFixed(2));
};
CreateInvoiceUtil.printPage = function() {
	console.log("Print Page");
	var e = $(".lineItems").children().length - 1;
	for ( var t = 1; t <= e; t++) {
		var n = $("#itemDesc\\." + t).val();
		if (n === "" || n === undefined) {
			$("#lineItem\\." + t).addClass("hide")
		}
	}
	var r = $("#attID").val();
	if (r === "") {
		$("#attID").addClass("hide");
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
		t = $("#itemDesc\\.0").offset().top
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
