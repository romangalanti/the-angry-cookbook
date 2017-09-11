const baseurl = 'http://localhost:8080/recipes';

function fillInDetails(data) {
	let html = `
		<h1>Title: ${data.title}</h1>
		<h2>Description: ${data.description}</h2>
		<div>Number of Minutes: ${data.numberOfMinutes}</div>
		<img src=${data.url}>
	`;
	for (let ingredient of data.ingredients) {
		html += `
			<form id="delete-ingredient-form" method="post" action="/recipes/${data.id}/ingredients/${ingredient.id}">
			<div>
				<b>Name of Food Item: ${ingredient.nameOfFoodItem}</b>
				<div>Unit of Measurement: ${ingredient.unitOfMeasurement}</div>
				<div>Quantity: ${ingredient.quantity}</div>
				<button>Delete Ingredient</button>
			</div>
			</form>
			<br>
		`;
	}
	
	html += `					
		<form id="create-ingredient-form" method="post" action="/recipes/${data.id}/ingredients">
			<input required id="nameOfFoodItem" name="nameOfFoodItem" placeholder="Name of Food Item">
			<br>
			<input required id="unitOfMeasurement" name="unitOfMeasurement" placeholder="Unit of Measurement">
			<br>
			<input id="quantity" name="quantity" placeholder="Quantity">
			<br>
			<button>Add Ingredient</button>
		</form>
	`;
	
	for (let instruction of data.instructions) {
		html += `
			<form id="delete-instruction-form" method="post" action="/recipes/${data.id}/instructions/${instruction.id}">
			<div>
				<b>Instruction: ${instruction.instruction}</b>
				<br>
				<img src=${instruction.url}>
				<br>
				<button>Delete Instruction</button>
			</div>
			</form>
			<br>
		`;
	}
	
	html += `					
		<form id="create-instruction-form" method="post" action="/recipes/${data.id}/instructions">
			<input required id="instruction" name="instruction" placeholder="Instruction">
			<br>
			<input id="url" name="url" placeholder="URL">
			<br>
			<button>Add Instruction</button>
		</form>
	`;
	
	$('#recipe-detail').html(html);
}

function createListElement(recipe) {
	$('.class-placeholder').remove();
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
			$('#recipe-detail').html("");
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '#delete-ingredient-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.remove();
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '#delete-instruction-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
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

$(document).on('submit', '#create-ingredient-form', function(e) {
	e.preventDefault();
	
	let payload = {
		nameOfFoodItem: $('#nameOfFoodItem').val(),
		unitOfMeasurement: $('#unitOfMeasurement').val(),
		quantity: $('#quantity').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (data) {
			fillInDetails(data);
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '#create-instruction-form', function(e) {
	e.preventDefault();
	
	let payload = {
		instruction: $('#instruction').val(),
		url: $('#url').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (data) {
			fillInDetails(data);
		})
		.fail(error => console.error(error));
});

$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	const recipeId = $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function(data) {
		fillInDetails(data);		
	}) 
});


$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let recipe of data) {
			createListElement(recipe);
		}
	} else {
		$('<li></li>')
			.addClass('class-placeholder')
			.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#recipe-list'));
	}
});