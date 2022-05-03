/*
Ethan McCarthy
COSC4345.001
2/28/2022
Implementation of the midpoint circle drawing algorithm
*/

function symmetry(x, y) {
  	var points = [];
  	points.push(x, y);
  	for (var i = 0; i < points.length; i++) {
  	    points[i] /= 100;
  	}
  	return points;
}

function thickenCircle(x, y, weight) {
	var points = [];
	points.push(x, y);
	for (var i = 1; i < weight; i++) {
		points.push(x + i, y + i);
		points.push(x - i, y + i);
		points.push(x + i, y);
		points.push(x, y + i);
		points.push(x + i, y - i);
		points.push(x - i, y - i);
		points.push(x - i, y);
		points.push(x, y - i);
	}
	return points;
}

function drawCircle(xc, yc, r, w) {
	var vertices = [];
	var points = [];
	
	var p = (5/4) - r;
	var x = 0;
	var y = r;
	var x2 = 2 * x;
	var y2 = 2 * y;

  	while (x <= y) {
		points.push(thickenCircle(x + xc, y + yc, w),
			thickenCircle(x + xc, -1 * (y - yc), w),
		        thickenCircle(-1 * (x - xc), y + yc, w),
		     	thickenCircle(-1 * (x - xc), -1 * (y - yc), w),
			thickenCircle(y + xc, x + yc, w),
			thickenCircle(y + xc, -1 * (x - yc), w),
			thickenCircle(-1 * (y - xc), x + yc, w),
			thickenCircle(-1 * (y - xc), -1 * (x - yc), w));

		if (p < 0) {
			p = p + x2 + 1;
		} else {
			p = p + x2 + 1 - y2;
			y--;
		}
		x2 = 2 * x + 2;
		y2 = 2 * y - 2;
    		x++;	
  	}

	for (var i = 0; i < points.length; i++) {
	    for (var j = 0; j < points[i].length; j++) {
		points[i][j] /= 100;
	        vertices.push(points[i][j]);
	    }
	}
	
  	return vertices;
}
