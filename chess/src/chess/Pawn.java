package chess;

public class Pawn extends Piece
{

	public Pawn(PieceColor color, Position position)
	{
		super(color, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Position newPosition, Piece[][] board)
	{
		int forwardDirection = color == PieceColor.White ? -1 : 1;
	      int rowDiff = (newPosition.getRow() - position.getRow()) * forwardDirection;
	      int colDiff = newPosition.getColumn() - position.getColumn();

	      // Forward move
	      if (colDiff == 0 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] == null) {
	          return true; // Move forward one square
	      }

	      // Initial two-square move
	      boolean isStartingPosition = (color == PieceColor.White && position.getRow() == 6) ||
	              (color == PieceColor.Black && position.getRow() == 1);
	      if (colDiff == 0 && rowDiff == 2 && isStartingPosition
	              && board[newPosition.getRow()][newPosition.getColumn()] == null) {
	          // Check the square in between for blocking pieces
	          int middleRow = position.getRow() + forwardDirection;
	          if (board[middleRow][position.getColumn()] == null) {
	              return true; // Move forward two squares
	          }
	      }

	      // Diagonal capture
	      if (Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null &&
	              board[newPosition.getRow()][newPosition.getColumn()].color != this.color) {
	          return true; // Capture an opponent's piece
	      }

	      return false;
	}

}
