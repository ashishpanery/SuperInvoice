function TodoCtrl($scope) {
	$scope.statusDetail = [
			{
				env : "QA1",
				url : "http://qaperf3:8654/monitor",
				status : "Ok",
				details : [
						"Everything is ok!" ]
			},
			{
				env : "QA2",
				url : "http://qaperf3:8654/monitor",
				status : "Error",
				details : [
						"<a href='http://storedev1.corp.apple.com/us'>UK - Home Page failed.</a> Failed to get value of required pass through parameter: dssid2",
						"<a href='http://storedev1.corp.apple.com/us'>UK - Browse ipad failed.</a> Read timed out." ]
			},
			{
				env : "QA3",
				url : "http://qaperf3:8654/monitor",
				status : "Error",
				details : [
						"<a href='http://storedev1.corp.apple.com/us'>UK - Home Page failed.</a> Failed to get value of required pass through parameter: dssid2",
						"<a href='http://storedev1.corp.apple.com/us'>UK - Browse ipad failed.</a> Read timed out.",
						"<a href='http://storedev1.corp.apple.com/us'>UK - Add to Cart failed.</a> Connect to /17.170.228.18:7378 timed out",
						"<a href='http://storedev1.corp.apple.com/us'>UK - Cart Page failed.</a> Cart ID not found." ]
			} ];
	
	$scope.refreshDetail = [{
		milliSeconds : "5000",
		display	: "5 sec"
	},
	{
		milliSeconds : "10000",
		display	: "10 sec"
	},
	{
		milliSeconds : "30000",
		display	: "30 sec"
	},
	{
		milliSeconds : "60000",
		display	: "1 min"
	},
	{
		milliSeconds : "120000",
		display	: "2 min"
	},
	{
		milliSeconds : "0",
		display	: "none"
	}
	];
	
	$scope.currentRefreshInterval = $scope.refreshDetail[0].milliSeconds;
	
	$scope.changeRefreshInterval = function() {
		$scope.timerFunction();
		console.log('$scope : ', $scope.currentRefreshInterval);	//Change timer function
	};
	
	$scope.request = null;
	$scope.timer = null;
	$scope.fetchData = function() {
		console.log('fetchData!');
		if($scope.request != null) {
			$scope.request.abort();
		}
		//TODO: Uncomment this later.
		/*
		$scope.request = $.ajax({
            type:'POST',
            url:'/data/',
            dataType:'json',
            cache: false,
            success:function(results){
            	$scope.statusDetail = results;
            }
        });
        */
	};
	
	$scope.$watch('$viewContentLoaded', function(){
	    console.log('we are here!!');
	    $scope.changeRefreshInterval();
	});
	
	$scope.timerFunction = function() {
		console.log('changing timer!');
		if($scope.timer != null) {
			clearInterval($scope.timer);
		}
		console.log('$scope.currentRefreshInterval : ', $scope.currentRefreshInterval);
		if(parseInt($scope.currentRefreshInterval) == 0) {
			clearInterval($scope.timer);
		} else {			
			$scope.timer = setInterval($scope.fetchData, parseInt($scope.currentRefreshInterval));
		}
	};
}

