$(function(){
    const appendTask = function(data){
      var taskCode = '<div class="cell-icon"> <a href="#" class="task-delete" data-id="' + data.id + '"> <img src="/img/deleteIcon.png" width="20px" height="20px" alt="удалить"></a></div>';

        if (data.done == true) {
            var cellFirst = ' <div class="cell"><input type="checkbox" data-id="' + data.id + '" checked>' +
                '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a>' +
                '<a href="#" class="task-put" data-id="' + data.id + '" data-name="' + data.name + '" data-desc="' + data.description + '"> <img src="/img/change.png" width="20px" height="20px" alt="изменить"></a></div> ';
            $('#task-list').append('<div class="line-done" data-id="' + data.id + '">' + cellFirst + taskCode + '</div>');
        } else {
          var cellFirst = ' <div class="cell"><input type="checkbox" data-id="' + data.id + '" >' +
                '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a>' +
                '<a href="#" class="task-put" data-id="' + data.id + '" data-name="' + data.name + '" data-desc="' + data.description + '"> <img src="/img/change.png" width="20px" height="20px" alt="изменить"></a></div> ';
            $('#task-list').append('<div class="line" data-id="' + data.id + '">' + cellFirst + taskCode + '</div>');
        }
    };

$(document).on('click','input[type="checkbox"]', function() {
    var taskId = $(this).data('id');
    $.ajax({
        method: "PATCH",
        url: '/todolist/'+ taskId,
        success: function(response)
        {
            if (document.querySelector('input[type="checkbox"][data-id="' + taskId +'"]').checked) {
            document.querySelector('div.line[data-id="' + taskId +'"]').className = "line-done";
            }
            else {
            document.querySelector('div.line-done[data-id="' + taskId +'"]').className = "line";
            }
        },
        error: function(response)
        {
            if ( response.status == 404) {
                alert('Задача не найдена');
            }
        }
    });
});
//    //Loading tasks on load page
//    $.get('/todolist/', function(response)
//    {
//        for(i in response) {
//            appendTask(response[i]); // task-list
//                    }
//    });

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });
  //Adding task
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/todolist/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });

    //Getting task description
    $(document).on('click','.task-link', function() {
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
                    method: "GET",
                    url: '/todolist/' + taskId,
                    success: function(response)
                    {
                        var code = '<div id="description-form" class="description" data-id="' + taskId + '"> <form> <h3> Описание </h3> <label>' + response.description +
                        '</label><form><hr> <button id="close-description" data-id="' + taskId + '">ОК</button> </div>';
                        if ((document.querySelector('div[id="description-form"][data-id="'+ taskId +'"]')) == null) {
                        link.parent().append(code);
                        }
                    },
                    error: function(response)
                    {
                        if ( response.status == 404) {
                           alert('Задача не найдена');
                        }
                    }
                });
                return false;
    });
//Delete Description
$(document).on('click','button#close-description', function() {
        var link = $(this).data('id');
        console.log(link);
        var element = document.querySelector('div[id="description-form"][data-id = "'+ link +'"]');
        console.log(element);
        element.parentNode.removeChild(element);
        return false;
        });

 //Delete task
     $(document).on('click','a.task-delete', function() {
            var link = $(this);
            var taskId = link.data('id');
            $.ajax({
                        method: "DELETE",
                        url: '/todolist/' + taskId,
                        success: function(response)
                        {
                            location.reload();
                            alert('Задача "' + response.name + '" удалена из БД');
                        },
                        error: function(response)
                        {
                            if ( response.status == 404) {
                               alert('Задача не найдена');
                            }
                        }
                    });

        });

//Show puting task form
    $(document).on('click','.task-put', function() {
        var link = $(this);
        var taskId = link.data('id');
        var taskName = link.data('name');
        var taskDesc = link.data('desc');

        var formPut = '<div id="task-put-form" data-id="' + taskId + '"><form><h2>Изменить задачу</h2>'+
                     '<label>Задача:</label><input type="text" name="name" value="' + taskName + '">'+
                     '<label>Описание:</label><input type="text" name="description" value="' + taskDesc + '"><hr><button id="put-task">Изменить</button></form></div>';
       if ((document.querySelector('div[id="task-put-form"][data-id="'+ taskId +'"]')) == null) {
           link.parent().append(formPut);
           }

    });

//Closing puting task form
        $('#task-put-form').click(function(event){
            if(event.target === this) {
     //           $(this).css('display', 'none');
            }
            });
//Put the task info according to input form data
    $(document).on('click','button#put-task', function() {
        var link =  $('#task-put-form') ;
        var taskId = link.data('id');
        var data = $('#task-put-form form').serialize();
        data = data + '&done=' + (document.querySelector('input[type="checkbox"][data-id="' + taskId +'"]').checked);
        console.log(data);
        $.ajax({
                        method: "PUT",
                        url: '/todolist/'+ taskId,
                        data: data,
                        success: function(response)
                                    {
                                        var task = {};
                                        task = response;
                                        location.reload();
                                    }
                                });
                                return false;
            });

 //search task containing the text in the to-do list
  $(document).on('click','input[type="submit"]', function() {
         var data = $('input[type="search"]').serialize();
         $.ajax({
                         method: "GET",
                         url: '/todolist/search',
                         data: data,
                         success: function(response)
                                     {
                                        document.querySelector('#task-list').innerHTML = '';
                                        for(i in response) {
                                            appendTask(response[i]);
                                            }
                                     }
                                 });
                                 return false;
             });

$('input[type="submit"]').trigger('search');

 //Getting do and done task description
    $(document).on('click','button[id="all-task"]', function() {
        $.ajax({
                    method: "GET",
                    url: '/todolist/',
                    success: function(response)
                    {
                        document.querySelector('button[id="all-task"]').className = "button-push";
                        document.querySelector('button[id="do-task"]').className = "button";
                        document.querySelector('button[id="done-task"]').className = "button";
                        document.querySelector('#task-list').innerHTML = '';
                        for(i in response) {
                            appendTask(response[i]); // task-list
                        }

                    }
                });
                return false;
    });

//Getting do task description
    $(document).on('click','button[id="do-task"]', function() {
        $.ajax({
                    method: "GET",
                    url: '/todolist/do',
                    success: function(response)
                    {
                        document.querySelector('button[id="all-task"]').className = "button";
                        document.querySelector('button[id="do-task"]').className = "button-push";
                        document.querySelector('button[id="done-task"]').className = "button";
                        document.querySelector('#task-list').innerHTML = '';
                        for(i in response) {
                            appendTask(response[i]); // task-list
                        }

                    }
                });
                return false;
    });

    //Getting done task description
        $(document).on('click','button[id="done-task"]', function() {
            $.ajax({
                        method: "GET",
                        url: '/todolist/done',
                        success: function(response)
                        {
                            document.querySelector('button[id="all-task"]').className = "button";
                            document.querySelector('button[id="do-task"]').className = "button";
                            document.querySelector('button[id="done-task"]').className = "button-push";
                            document.querySelector('#task-list').innerHTML = '';
                            for(i in response) {
                                appendTask(response[i]); // task-list
                            }

                        }
                    });
                    return false;
        });
});
