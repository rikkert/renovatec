package net.ripencc.compo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import net.ripencc.compo.dto.Snake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static net.ripencc.compo.dto.Move.Direction.*;

// Simple out-of-bounds algorithm
@Service
public class Gokcem {


    private static int maxDepth = 2;

    @Autowired
    public Gokcem() {

    }

    public Move move(Battle battle) {
        var result = Move.builder();

        Move.Direction  bestDirection = getNextPossibleMovePoints(battle);

        return result.direction(bestDirection).build();
    }

    private Move.Direction getNextPossibleMovePoints(Battle battle) {
        Set<Move.Direction> allMoves = Set.of(up,down,right,left);

        Optional<MovePoint> mp =
                allMoves.stream().map(d -> justMove(d,battle,0))
                        .max(Comparator.comparing(ft -> ft.getValue()));

        return mp.get().getDirection();
    }

    private MovePoint justMove(Move.Direction d, Battle battle, int depth){
        if(depth ==maxDepth){
            return new MovePoint(d,0);
        }
        Board board = battle.getBoard();
        Point head = battle.getYou().getHead();
        //remove the tail of the snake?
        Set<Point> occupied = battle.getBoard().getSnakes().stream()
                .flatMap(snake -> snake.getBody().stream())
                .collect(Collectors.toSet());

        if(d.equals(up)){
            Point next =new Point(head.x , head.y+1) ;
            if (isPointInTheWallOrOccupied(board,occupied,next)){
                return new MovePoint(up,-1000);
            }
            return nextMove(battle,up,next,depth+1);
        }
        else if(d.equals(down)){
            Point next =new Point(head.x , head.y-1) ;
            if (isPointInTheWallOrOccupied(board,occupied,next)){
                return new MovePoint(down,-1000);
            }
            return nextMove(battle,down,next,depth+1);
        }
        else if(d.equals(right)){
            Point next = new Point(head.x+1 , head.y);
            if (isPointInTheWallOrOccupied(board,occupied,next)){
                return new MovePoint(right,-1000);
            }
            return nextMove(battle,right,next,depth+1);
        }
        else if(d.equals(left)){
            Point next = new Point(head.x-1 , head.y);
            if (isPointInTheWallOrOccupied(board,occupied,next)){
                return new MovePoint(left,-1000);
            }

            return nextMove(battle,left,next,depth+1);
        }
        else return null;
    }

    private Battle giveMeNextBattle(Battle battle, Point next) {

        battle.getBoard().getSnakes().remove(battle.getYou());
        Snake oldYou = battle.getYou();
        var current = new ArrayList<>(oldYou.getBody());
        current.add(next);
        Snake newYou = Snake.builder().id(oldYou.getId()).body(current).head(next).build();
        List newSnakeList =
                battle.getBoard().getSnakes().stream().filter(a->a.equals(oldYou)).collect(Collectors.toList());
        newSnakeList.add(newYou);

        var newBoard = Board.builder().height (battle.getBoard().getHeight())
                .width(battle.getBoard().getWidth())
                .food( battle.getBoard().getFood())
                .snakes(newSnakeList)
                .hazards( battle.getBoard().getHazards()).build();

        return Battle.builder().board(newBoard).build();

    }

    private double calculatePoint(Board b, Point next,double avarage){

        if(b.getFood().contains(next)){
            return avarage + 150 ;
        }
        else if(b.getHazards().contains(next)) {
            return avarage + 80;
        } else {
            return avarage+ 100;
        }
    }
    private boolean isPointInTheWall(Point p , Board board){
        if(p.x >= 0 && p.x < board.getWidth()){
            return true;
        } else if(p.y >= 0 && p.y < board.getHeight()){
            return true;
        }
        return false;
    }

    private boolean isPointInTheWallOrOccupied(Board board,Set<Point> occupied, Point next){
        if(occupied.contains(next) && isPointInTheWall(next,board)){
            return false;
        }
        return true;
    }

    private MovePoint nextMove(Battle battle, Move.Direction d ,Point next, int depth){
        Set<Move.Direction> allMoves = Set.of(up,down,right,left);
        Battle nextBattle = giveMeNextBattle(battle,next); //approc=ximatelt

        var avg = allMoves
                .stream()
                .map(s -> justMove(s,nextBattle,depth+1))
                .filter(Objects::nonNull)
                .mapToDouble(MovePoint::getValue)
                .average();
        double newValue = calculatePoint(battle.getBoard(),next,avg.getAsDouble());
        return new MovePoint(d,newValue);
    }
}

