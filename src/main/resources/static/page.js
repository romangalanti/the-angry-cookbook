const baseurl = 'http://localhost:8080/recipes';

function fillInDetails(data) {
	console.log(JSON.stringify(data))
	
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
			<button>Delete</button>
			<br>
		</div>
		</form>
		`;
	}
		
		html += `		
				<form id="create-ingredient-form" method="post" action="/recipes/${data.id}/ingredients">
					<input name="nameOfFoodItem" id="nameOfFoodItem" placeholder="Name of Food Item">
					<br>
					<input name="unitOfMeasurement" id="unitOfMeasurement" placeholder="Unit of Measurement">
					<br>
					<input name="quantity" id="quantity" placeholder="Quantity"> 
					<br>
					<button>Add this ingredient</button>
				</form>
		`;
		
		
		for (let instruction of data.instructions) {
			html += `
			<form id="delete-instruction-form" method="post" action="/recipes/${data.id}/instructions/${instruction.id}">
			<div>
				<b>Instruction: ${instruction.instruction}</b>
				<button>Delete</button>
				<br>
			</div>
			</form>
			`;
		}
		
		html += `		
			<form id="create-instruction-form" method="post" action="/recipes/${data.id}/instructions">
				<input name="instruction" id="instruction" placeholder="Instructions">
				<br>
				<button>Add this instruction</button>
			</form>
	`;
		
		$('#recipe-detail').html(html);
	
};



//CREATES RECIPE LIST
//function to create the list item and put a recipe in it
function createListElement(recipe) {
	$('.ugly-dumbass-placeholder').remove();
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
	
};



//LINKS DELETE BUTTON TO DELETE RECIPE FUNCTION
//.on(eventName, filter, callback (in this instance our callback is a function which it will then call))
$(document).on('submit', '.delete-recipe-form', function (e) {
	e.preventDefault(); 
	
	$.ajax(this.action, {type: 'DELETE'})
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
	$.ajax(this.action, {type: 'DELETE'})
		.done(() => {
			$(this)
				.remove();
		})
		
		.fail(error => console.error(error));
}); 

//DELETE INSTRUCTION PART -- MAY NEED TO CHANGE
$(document).on('submit', '#delete-instruction-form', function (e) {
	e.preventDefault(); 
	$.ajax(this.action, {type: 'DELETE'})
		.done(() => {
			$(this)
				.remove();
		})
		
		.fail(error => console.error(error));
}); 

//CREATES A NEW RECIPE
//subscribes to the button click
$('#create-recipe-form').on('submit', function(e) {

	e.preventDefault(); 
	console.log(e); 
	
	//defines a new object payLoad
	let payload = {
		title: $('#title').val(),
		description: $('#description').val(),
		numberOfMinutes: $('#numberOfMinutes').val(),
		url: $('#url').val()
	};
	
	let ajaxOptions = {
		type: "POST", 
		//converts an object to a string in JSON
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};

	
	//$. notation = global function that is defined by jQuery (like ajax calls) -- can replace jQuery. with $.
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe) {
			createListElement(recipe); 
		})
		.fail(error => console.error(error));
	
});


//CREATES AND ADDS AN INGREDIENT TO A RECIPE
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


//adds an instruction
$(document).on('submit', '#create-instruction-form', function(e) {
	
	e.preventDefault();
	
	let payload = {
			instruction: $('#instruction').val()
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



//DISPLAYS RECIPE DETAILS WHEN CLICK ON TITLE
//how you take a normal document body object and do things with it
$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	const recipeId = $(this).data('recipeId');
	
	
	$.getJSON(baseurl + '/' + recipeId, function(data) {
		fillInDetails(data); 

	}); 
});


$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let recipe of data) {
			createListElement(recipe);
		}
	} else {
		$('<li></li>')
			.addClass('ugly-dumbass-placeholder')
			.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#recipe-list'));
	}
});