window.onload = function(){
    var btnHide = document.getElementById('btn-hide');
    taskAdd = document.getElementById('taskAdd');
    btnShow = document.getElementById('btn-show');

    btnHide.onclick = display;
    btnShow.onclick = display;
}

function display(){

    if(this.value == '-'){
        taskAdd.style.display = 'none';
        btnShow.style.display = 'block';
    }else {
        taskAdd.style.display = 'block';
        btnShow.style.display = 'none';
    }
}

