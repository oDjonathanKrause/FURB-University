const TAMANHO_MATRIZ = 7;
const TAMANHO_CANVAS = 601;

const MAPA = [
  [0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 1, 1, 0, 0],
  [0, 0, 1, 0, 0, 0, 1],
  [0, 0, 0, 0, 1, 1, 0],
  [0, 1, 0, 1, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0]
]

function setup() {
  createCanvas(TAMANHO_CANVAS, TAMANHO_CANVAS);
  
  background(255);
  let x, y;
  for (let i = 0; i < TAMANHO_MATRIZ; i++) {
    for (let j = 0; j < TAMANHO_MATRIZ; j++) {
      
      if (MAPA[j][i] == 1) 
        fill(0);
      else
        fill(255);

      x = i * 80;
      y = j * 80;
      rect(x, y, 80, 80);
    }
  }
}