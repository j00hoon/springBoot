// home.html의 chart코드 부분 밑에 있는 JS에서 선언한 변수 chartData를 갖고와서, decodeHtml function에 집어넣는다
var chartDatastr = decodeHtml(chartData);
var chartJsonArr = JSON.parse(chartDatastr);


var arrayLength = chartJsonArr.length;
var numericData = [];
var labelData = [];

for(var i = 0; i < arrayLength; i++){
	numericData[i] = chartJsonArr[i].value;
	labelData[i] = chartJsonArr[i].label;
}



// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["deeppink", "cyan", "purple"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title:{
    		display:true,
    		text:"Project Stage"
    	}
    }
});


// [{"value":1, "label":"COMPLETED"}, {"value":2, "label":"INPROGRESS"}, {"value":1, "label":"NOTSTARTED"}]
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	
	return txt.value;
}


