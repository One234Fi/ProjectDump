function MatrixMultiply3D(u, v)
{
  var w = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0] ];
  for (var i=0; i<4; i++)
  {
    for (var j=0; j<4; j++)
    {
      w[i][j] = (u[i][0] * v[0][j]) + (u[i][1] * v[1][j]) + (u[i][2] * v[2][j]) + (u[i][3] * v[3][j]);
    }
  }
  return w;
}

function VectorMultiply3D(m, p)
{
  var q = [];
  for (var i=0; i<4; i++)
  {
    q[i] = (m[i][0] * p[0]) + (m[i][1] * p[1]) + (m[i][2] * p[2]) + (m[i][3] * p[3]);
  }
  return q;
}

function crossProduct(a, b)
{
  var rv = [];
  rv[0] = a[1]*b[2] - a[2]*b[1];
  rv[1] = a[2]*b[0] - a[0]*b[2];
  rv[2] = a[0]*b[1] - a[1]*b[0];
  return rv;
}

function magnitude(v)
{
  return Math.sqrt(v[0]*v[0] + v[1]*v[1] + v[2]*v[2]);
}

function dotProduct(V1, V2)
{
  return V1[0]*V2[0] + V1[1]*V2[1] + V1[2]*V2[2];
}