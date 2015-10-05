$ ->
  $.get "/get-musics", (musics) ->
    $.each musics, (index, music) ->
      title = $("<div>").addClass("title").text music.title
      lyrics = $("<div>").addClass("lyrics").text music.lyrics
      year = $("<div>").addClass("year").text music.year

      $("#musics").append $("<li>").append(title) #.append(lyrics).append(year)
