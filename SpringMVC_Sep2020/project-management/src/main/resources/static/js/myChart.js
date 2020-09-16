var chartDataSTR = decodeHtml(chartData);
var chartJsonArr = JSON.parse(chartDataSTR);




var arrayLength = chartJsonArr.length;

var numericData = [];
var labelData = [];
 
for(var i = 0; i < arrayLength; i++){
	numericData[i] = chartJsonArr[i].value;
	labelData[i] = chartJsonArr[i].label;
}



new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title:{
    		display: true,
    		text: 'Project Statuses'
    	}
    }
});


// [{"value" : 1, "label" : "COMPLETED"}, {"value" : 1, "label" : "INPROGRESS"}, {"value" : 1, "label" : "NOTSTARTED"}]
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}