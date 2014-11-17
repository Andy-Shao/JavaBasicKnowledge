/*
var MyRun = Java.extend(java.lang.Runnable, {
	run: function(){
		print("Run in separate thread")
	}
});
var th = new java.lang.Thread(new MyRun());
th.start();*/

//var th = new java.lang.Thread(function() {print("Run in separate thread")});
var th = new java.lang.Thread(function() print("Run in separate thread"));
//If you use th.start() then can't show the message
th.run();