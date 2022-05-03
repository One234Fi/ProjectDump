function MatrixMultiply3D(u, v)
{
  var w = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  for (var i=0; i<3; i++)
  {
    for (var j=0; j<3; j++)
    {
      w[i][j] = (u[i][0] * v[0][j]) + (u[i][1] * v[1][j]) + (u[i][2] * v[2][j]);
    }
  }
  return w;
}

function VectorMultiply3D(m, p)
{
  var q = [];
  for (var i=0; i<3; i++)
  {
    q[i] = (m[i][0] * p[0] + m[i][1] * p[1] + m[i][2] * p[2]);
  }
  return q;
}


