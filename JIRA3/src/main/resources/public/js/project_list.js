 $ul = $('ul');
$.getJSON( "/project/list", function( data ) {
	var items = [];
	  $.each( data, function( key, val ) {
	    items.push( "<li id='" + key + "'>" + val.name + "</li>" );
	  });
	 
	  $( "<ul/>", {
	    "class": "my-new-list",
	    html: items.join( "" )
	  }).appendTo( "body" );
});
