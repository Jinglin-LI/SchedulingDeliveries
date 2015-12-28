import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class SchedulingDeliveries {
    List<Mother> motherList = new ArrayList<Mother>();
	public static int globalQueueNumber = 0;
	
	Comparator<Mother> motherComparator = new Comparator<Mother>() {
		public int compare(Mother m1, Mother m2) {
			if (m1.dilation > m2.dilation) {
				return 1;
			}
            else if (m1.dilation < m2.dilation) {
				return -1;
			}
            else {
				return m1.queueNumber <= m2.queueNumber ? 1 : -1;  
			}
		}
	};
	
	public class Mother{
		private String name;
		private int dilation;
		private int queueNumber;
		
		Mother(String name, int dilation){
			this.name = name;
			this.dilation = dilation;
			this.queueNumber = ++ globalQueueNumber;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDilation() {
			return dilation;
		}

		public void setDilation(int dilation) {
			this.dilation = dilation;
		}

		public int getQueueNumber() {
			return queueNumber;
		}

		public void setQueueNumber(int queueNumber) {
			this.queueNumber = queueNumber;
		}
	}

  void ArriveAtHospital(String womanName, int dilation) {
	  motherList.add(new Mother(womanName, dilation));
  }

  void UpdateDilation(String womanName, int increaseDilation) {
    
	  for (Mother mom : motherList) {
			if (mom.getName().equals(womanName)) {
				mom.dilation += increaseDilation;
				break;
			}

		}

  }

  void GiveBirth(String womanName) {
	  for (Mother mom : motherList) {
			if (mom.getName().equals(womanName)) {
				motherList.remove(mom);
				break;
			}
		}
  }

  String Query() {
    String ans = "The delivery suite is empty";

    // You have to report the name of the woman that the doctor
    // has to give the most attention to. If there is no more woman to
    // be taken care of, return a String "The delivery suite is empty"

    if (!motherList.isEmpty()) {
    	Collections.sort(motherList,motherComparator);
    	Mother lastMother = motherList.get(motherList.size() - 1);
    	return lastMother.getName();
	}

    return ans;
  }

  void run() throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
    while (numCMD-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 1: UpdateDilation(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 2: GiveBirth(st.nextToken()); break;
        case 3: pr.println(Query()); break;
      }
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    SchedulingDeliveries ps1 = new SchedulingDeliveries();
    ps1.run();
  }
}
