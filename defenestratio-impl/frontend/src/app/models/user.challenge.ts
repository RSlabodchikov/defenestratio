import {ChallengeResult} from "./challenge.result";
import {ChallengeModel} from "./challenge.model";

export class UserChallenge {
  id: string;
  status: string;
  dueDate: Date;
  userId: string;
  challenge: ChallengeModel;
  challengeResult: ChallengeResult
}
