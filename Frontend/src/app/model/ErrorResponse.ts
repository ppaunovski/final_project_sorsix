export interface ErrorResponse {
  message: string | undefined;
  status: number;
  error: string;
  timestamp: Date;
}
