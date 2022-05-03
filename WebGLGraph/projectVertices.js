/*
Ethan McCarthy
COSC 4345.001
2/8/2022
Description: The function graphPoints() holds a variable with all the points used by the HTML file's main method to draw the image.
*/

function graphPoints() {

 	var vertices = [
       		-0.95, 0.0,
		-0.95, -1,
	       	-0.65, -1,
		-0.65, 0.0,

		-0.55, -0.3,
		-0.55, -1,
		-0.25, -1,
		-0.25, -0.3,

		-0.15, 0.2,
		-0.15, -1,
		0.15, -1,
		0.15, 0.2,

		0.55, 0.5,
		0.55, -1,
		0.25, -1,
		0.25, 0.5,

		0.95, 0.3,
		0.95, -1,
	       	0.65, -1,
		0.65, 0.3,			
		-0.8, 0.1,
		-0.4, -0.2,
		0, 0.3,
		0.4, 0.6,
		0.8, 0.4,

		0.81, 0.39,
		0.74, 0.37,
		0.79, 0.46
	];
	return vertices;
}