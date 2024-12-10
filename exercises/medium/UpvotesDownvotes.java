
import java.util.Scanner;

public class UpvotesDownvotes {
        public static int getVoteCount(int upVotes,int downVotes) {
            if (upVotes>downVotes)
                return upVotes-downVotes;
            else
                return -(downVotes-upVotes);

        }

    public static void main(String[] args) {
            Scanner s=new Scanner(System.in);
            System.out.println("Enter Upvotes : ");
            int upVotes=s.nextInt();

            System.out.println("Enter Downvotes : ");
            int downVotes=s.nextInt();

        System.out.println("Total vote : "+getVoteCount(upVotes,downVotes));
    }

}
