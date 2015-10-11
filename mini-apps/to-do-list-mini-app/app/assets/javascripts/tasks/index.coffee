$ ->
    $.get "/get-tasks", (tasks)->
        $.each tasks, (index, task) ->
            description = $("<div>").addClass("name").text task.description

            $("#tasks").append $("<li>").append(description)