 $ul = $('ul');
$.getJSON( "/project/list", function( data ) {
	var items = [];
	  $.each( data, function( key, val ) {
	    items.push( "<li id='" + key + "'>" + val.name + "</li>" );
	  });

$("#mydiv").attr("class", "container");
	  $( "<ul/>", {
	    "class": "my-new-list",
	    html: items.join( "" )
	  }).appendTo( "body" );
});
