var exec = require('cordova/exec');

function MegaduckGestures() {}

MegaduckGestures.prototype.swiper = function(callBack) {
	exec(callBack, function(result){
		console.log("Error" + " result "+result);
	},"MegaduckGestures","",[]);
};

var megaduckGestures = new MegaduckGestures();
module.exports = megaduckGestures;
