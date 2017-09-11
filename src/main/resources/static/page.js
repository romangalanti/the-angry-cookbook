const baseurl = 'http://localhost:8080/recipes';

function createListElement(recipe) {
	$('<li></li>')
	.html(`
		<a href="#" data-recipe-id="${recipe.id}">
			${recipe.title}
		</a>
		<form class="delete-recipe-form" method="post" action="/recipes/${recipe.id}">
			<button>Delete</button>
		</form>
	`)
	.appendTo($('#recipe-list'));
}

$(document).on('submit', '.delete-recipe-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('li')
				.remove();
		})
		.fail(error => console.error(error));
});

$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();
	
	let payload = {
		title: $('#title').val(),
		description: $('#description').val(),
		numberOfMinutes: $('#numberOfMinutes').val(),
		url: $('#url').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe) {
			createListElement(recipe);
		})
		.fail(error => console.error(error));
});

$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	const recipeId = $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function(data) {
		$('#recipe-detail')
		//data.company = data.company || '<i>no company specified</i>';
			.html(`
					<h1>Title: ${data.title}</h1>
					<h2>Description: ${data.description}</h2>
					<div>Number of Minutes: ${data.numberOfMinutes}</div>
					<img src=${data.url}>
			`);
	}) 
});


$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let recipe of data) {
			createListElement(recipe);
		}
	} else {
		$('<li></li>')
			.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#recipe-list'));
	}
});