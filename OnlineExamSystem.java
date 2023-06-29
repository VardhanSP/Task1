import java.util.*;
     
public class OnlineExamSystem{
    public String Username;
    public String Password;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;
    
    public OnlineExamSystem(String username, String password){
        this.Username=username;
        this.Password=password;
        System.out.println("Sucessfully You are resgistered!   :");
        this.isLoggedIn=false;
        this.timeRemaining=10;
        this.questionCount=10;
        this.userAnswers=new int[questionCount];
        this.correctAnswers=new int[questionCount];
        for(int i =0;i< questionCount;i++){
            correctAnswers[i]=(int)Math.round(Math.random());
        }
        
    } 
    public void login(){
        System.out.println("Log in to give the Exam");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername=scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword=scanner.nextLine();
        if (inputUsername.equals(Username) && inputPassword.equals(Password)){
            isLoggedIn=true;
            System.out.println("Login Successful Best of Luck Dear");
        }else{
            System.out.println("Login failed.Please try again.");
        }
    }
    public void logout(){
        isLoggedIn=false;
        System.out.println("Login successful.");
    }
    public void startExam(){
        if(!isLoggedIn){
            System.out.println("Please login First.");
            return;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("You Have" + timeRemaining +"minutes to complete the exam.");
        for (int i = 0;i < questionCount; i++){
            System.out.println("Question "+(i + 1) + ":");
            System.out.println("1.option 1");
            System.out.println("2.option 2");
            System.out.println("Your answer(1 or 2): ");
            int answer=scanner.nextInt();
            userAnswers[i]=answer;
       }
       System.out.println("Would like to submit?\n1:Yes \n2:No");
       int n=scanner.nextInt();
       if (n == 1){
        submitExam();
    }else{
        //auto-submit when time is up
        try{
            Thread.sleep(timeRemaining * 10 * 1000);
        }catch(InterruptedException e){
            e.printStackTrace();
            submitExam();
        }

    }
}
public void submitExam(){
    if(!isLoggedIn){
        System.out.println("pleaase login first.");
        return;
    }
    int score=0;
    for(int i =0;i < questionCount; i++){
        if(userAnswers[i]==correctAnswers[i]){
            score++;
        }
    }
    System.out.println("Your score is "+ score +" out of " + questionCount + ".");
    logout();
}
public static void main(String[]args){
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter your username and password");
    String uName=sc.nextLine();
    String pWord=sc.nextLine();
    OnlineExamSystem examSystem=new OnlineExamSystem(uName , pWord);
    examSystem.login();
    examSystem.startExam();
}
}