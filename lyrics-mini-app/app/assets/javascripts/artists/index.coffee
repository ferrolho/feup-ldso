$ ->
  $.get "/get-artists", (artists) ->
    $.each artists, (index, artist) ->
      name = $("<div>").addClass("name").text artist.name
      $("#artists").append $("<li>").append(name)
