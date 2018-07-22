
var accountlist = new Array();

function ready(){
	$.getJSON( "PrintService.do", function( data ) {
	
		$( "#partyName" ).autocomplete({
			source: data["party"],
			duration : 50		
			});
		
		var accountData = data["accountdata"];
		
		for(var i = 0; i< accountData.length;i++){
      	  var opt = '<option  value="'+accountData[i]["acnumber"]+'" balance="'+accountData[i]["balance"]+'" bank="'+accountData[i]["bank"]+'" bank="'+accountData[i]["acholder"]+'">'+accountData[i]["acnumber"]+'</option>';
      	  $('#acnumber').append(opt);
        }
	});
}


function getChequeHistory(){
	$.getJSON( "ChequeDetail.do", function( data ) {
		scopeArray = data["data"];
	});
}

function printClick(requestType){

	var bankType = $('#bankTemplate').val();
	var party = $('#partyName').val();
	var amount = $('#amount').val();
	var chequeNo = $('#chequeno').val();
	var issueDate = $('#issueDate').val();
	var isAcPayee  = $('#acpayee').is(':checked');
	var isBearer = $('#bearer').is(':checked');
	var amountInWords = $('#amountInWords').val();
	var chequeprinter = $('#chequeprinter').val();
	var acnumber = $('#acnumber').val();
	var remarks = $('#remarks').val();
	
	if(party==""){
		alert("Please provide party name.");
		$('#partyName').focus();
		return;
	}
	if(amount == ""){
		alert("Amount can't be empty. Please provide amount.");
		$('#amount').focus();
		return
	}
	if(chequeNo == ""){
		alert(" Please provide cheque number.");
		$('#chequeNo').focus();
		return
	}
	if(amountInWords == ""){
		alert(" Please provide amounts in word");
		$('#amountInWords').focus();
		return
	}
	if(acnumber == ""){
		alert(" Please provide account number.");
		$('#acnumber').focus();
		return
	}
	
	var payeeType = '';
	if(isAcPayee){
		
		payeeType = "A/C Payee";
		
	}else{
		payeeType = "Bearer";
		
	}
	$.ajax({
		url : 'CheckPrint.do?bankType='+bankType+'&party='+party+'&chequeprinter='+chequeprinter+'&amount='+amount+'&amountInWords='+amountInWords+'&chequeno='+chequeNo+'&issueDate='+issueDate+'&payeetype='+payeeType+'&acnumber='+acnumber+'&remarks='+remarks+'&requestType='+requestType,
		type:'POST',
		dataType: "json",
		
	}).done(
			function(data){
				var url = "report/"+data["timestamp"]+".pdf";
				}
	);

};
function hto(ff){
	var sNum3 = ""
	var p1=""
	var p2=""
	var p3=""

	var hy=""
 var n1 = new Array
    ('', 'one', 'two', 'three', 'four', 'five', 'six',
    'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve',
    'thirteen', 'fourteen', 'fifteen',  'sixteen', 'seventeen',
    'eighteen', 'nineteen')

  var n2 = new Array('', '', 'twenty', 'thirty', 'forty',
    'fifty', 'sixty', 'seventy', 'eighty', 'ninety')


	sNum3 = ff
	var j =  sNum3.length
	var h3 = sNum3.substring((j-3),(j-3)+1)
		if (h3 > 0) {
		p3= n1[h3] + " hundred "}
		else {p3=""}

	var t2 = parseInt(sNum3.substring((j-2),(j-2)+1), 10)
	var u1 = parseInt(sNum3.substring((j-1),(j-1)+1), 10)
	var tu21 = parseInt(sNum3.substring((j-2),(j-2)+2), 10)

		if (tu21 == 0) {
		 p1="";
		 p2="";
				}

		else if ((t2 < 1) && (u1 > 0)) {
			p2="";
			p1= n1[u1]
					}

		else if (tu21 < 20) {
			p2="";
			p1= n1[tu21]
					}

		else if ((t2 > 1) && (u1 == 0)) {
			p2= n2[t2]
			p1=""
					}

		else {
			p2= n2[t2] + "-"
			p1=n1[u1] 
				}

	ff = p3 + p2 + p1

  return ff;
};

function stripBad(string) {
    for (var i=0, output='', valid="0123456789."; i<string.length; i++)
       if (valid.indexOf(string.charAt(i)) != -1)
          output += string.charAt(i)
    return output;
};

function stripBad2(string) {
    for (var i=0, output='', valid="0123456789"; i<string.length; i++)
       if (valid.indexOf(string.charAt(i)) != -1)
          output += string.charAt(i)
    return output;
};
function GetNumber2d(ele) {
	//http://www.easysurf.cc/cnvert18.htm
	var gf = ""
	var gg = ""
	var gh = ""
	var gi = ""
	var gj = ""
	var gc = "zero"
	var dd = ""
	var cc = ""
		var money = ele.value;
	var sNumber = '';
	var sNumbec = '';
	console.log(money.indexOf('.'));
	if(money.indexOf('.')>-1){
		 sNumber = money.substring(0,money.indexOf('.'));
		 sNumbec = money.substring(money.indexOf('.')+1,money.length);
	}else{
		sNumber = money;
		sNumberc = '';
	}
console.log(sNumbec+","+sNumber);
	sNumber = stripBad(sNumber);
	sNumber = parseInt(sNumber, 10);
	var sNum2 = String(sNumber);

	sNumbec = stripBad(sNumbec);
	sNumbec = parseInt(sNumbec, 10);
	var sNumc = String(sNumbec);

	if (sNumber == 1) {
		dd = " rupee "
	} else {
		dd = " rupees "
	}
	dd = "";
	if (sNumbec == 1) {
		cc = " paisa"
	} else {
		cc = " paise"
	}

	if (sNumbec < 1) {
		gc = "zero"
	}
	if (sNumc == "") {
		gc = "zero"
	}
	if (sNumc > 0) {
		gc = hto(sNumc)
	}

	var j = sNum2.length
	var hNum2 = sNum2.substring((j - 3), ((j - 3) + 3));
	console.log("before thousand="+hNum2);
	if (hNum2 > 0) {
		gf = hto(hNum2) + ""
	}

	var tNum2 = sNum2.substring((j - 5), (j - 5) + 2);
	console.log("st2="+tNum2);
	if (tNum2 > 0) {
		gg = hto(tNum2) + " thousand "
	}

	var mNum2 = sNum2.substring((j - 7), (j - 7) + 2);
	console.log("st3="+mNum2);
	if (mNum2 > 0) {
		gh = hto(mNum2) + " lakh "
	}

	var bNum2 = sNum2.substring((j - 9), (j - 9) + 2);
	console.log("st4="+bNum2);
	if (bNum2 > 0) {
		gi = hto(bNum2) + " crore "
	}

	var trNum2 = sNum2.substring((j - 11), (j - 11) + 2)
	trNum2 = 0;
	if (trNum2 > 0) {
		gj = hto(trNum2) + " arab "
	}

	if (sNumber < 1) {
		gf = "zero"
	}

	if (j > 15) {
		gj = " Your number is too big for me to spell";
		gi = "";
		gh = "";
		gg = "";
		gf = "";
	}
	var dds = gj + gi + gh + gg + gf;
	if (dds == "") {
		dds = "zero"
	}
	var amountInWords= " " + dds + dd + "  and   " + gc + " " + cc+" only";
	var str = amountInWords.toLowerCase();
	str = str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
	$('#amountInWords').val(str);
};
function numeralsOnly(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : 
        ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46)) {
        return false;
    }
    return true;
}


