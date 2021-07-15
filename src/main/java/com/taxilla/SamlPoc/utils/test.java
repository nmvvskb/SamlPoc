package com.taxilla.SamlPoc.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.client.RestTemplate;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

public class test {
    public static void main(String ar[]) throws IOException {

//        File metaData =  new File("./src/main/resources/saml/metadata/okta.xml");
//        byte[] metaContent = FileUtils.readFileToByteArray(metaData);
//        Base64 base64 = new Base64();
//        byte[] base64EncodeByteArray = base64.encode(metaContent);
//        String base64EncodedMessage = new String(base64EncodeByteArray);
//
//        System.out.println("base64EncodedMessage : " + base64EncodedMessage);
//        String endPointUrl = "https://taxilla4.okta.com/app/taxilla4_samplesamlwithspringandocta_1/exk75btfXVhh0TSqe695/sso/saml?SAMLRequest="+base64EncodedMessage;
//
//
////        String urlEncodedMessage = URLEncoder.encode(base64EncodedMessage,"UTF-8");
////        System.out.println("urlEncodedMessage : " + urlEncodedMessage);
//
//        byte[] decode = base64.decode("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbDJwOlJlc3BvbnNlIERlc3RpbmF0aW9uPSJodHRwOi8vbG9jYWxob3N0OjkwOTAvc2FtbC9TU08iIElEPSJpZDQxNDMzNjk2MjEyMjAxNzMxMjM2NjQ0MzYiIEluUmVzcG9uc2VUbz0iYTI4MDhjOWZoN2ljajhqajQ2MGppZDJlZzU1aTBoZyIgSXNzdWVJbnN0YW50PSIyMDIxLTA3LTE0VDA5OjE4OjI4LjM3M1oiIFZlcnNpb249IjIuMCIgeG1sbnM6c2FtbDJwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPjxzYW1sMjpJc3N1ZXIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDplbnRpdHkiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj5odHRwOi8vd3d3Lm9rdGEuY29tL2V4azc1YnRmWFZoaDBUU3FlNjk1PC9zYW1sMjpJc3N1ZXI+PGRzOlNpZ25hdHVyZSB4bWxuczpkcz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PGRzOlNpZ25lZEluZm8+PGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48ZHM6U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxkc2lnLW1vcmUjcnNhLXNoYTI1NiIvPjxkczpSZWZlcmVuY2UgVVJJPSIjaWQ0MTQzMzY5NjIxMjIwMTczMTIzNjY0NDM2Ij48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8+PC9kczpUcmFuc2Zvcm1zPjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz48ZHM6RGlnZXN0VmFsdWU+Qnl0LytPZ2w4T2tMYjBBWHpTeEloN0RET25kckcvc0E5K0g2VmlwVnVtbz08L2RzOkRpZ2VzdFZhbHVlPjwvZHM6UmVmZXJlbmNlPjwvZHM6U2lnbmVkSW5mbz48ZHM6U2lnbmF0dXJlVmFsdWU+SWxCdTNPenp6OXNJUE01aDNJZkdtOUNZTWRnbkNDS3p2MEV2akVJQzBieWp1bW95M0dFWi9Yc0VFY09RWmJXKytTTXZJenJudmxMMG1WQzVZY2ZCc2cwRDZ0djN0VUl5eHlkcWNrUXRkek90OVM3TzdsNEVwdE9ONXlXcGVRRDZpMlRxOWVsd2tmWDFZUmVmVnI5UmZxcTdwcENYMXhmYXJUV0c1RllURndEbkZXWmxpajBsWWJqYXMza0JPSHRnVVdNNWY1eHYwVkU2RGtGdXp6R25NYnh4Ry9rQmV3b1RobWcrRlRNMXFTZVdVUnJoSnlBQTQ2djBFNi9BajN5V2R1YTNoS2pFOEF5U0VzRFl1SGFYMjFwUHp5Unp3M3NWNE04c3hJblp6TnI2dXAzb0x3YVhETlpBaDZWZlo3aXdYZkk3M2tpQU9FNzJYRktLSjdqMjhnPT08L2RzOlNpZ25hdHVyZVZhbHVlPjxkczpLZXlJbmZvPjxkczpYNTA5RGF0YT48ZHM6WDUwOUNlcnRpZmljYXRlPk1JSURvRENDQW9pZ0F3SUJBZ0lHQVhwaDRCeWhNQTBHQ1NxR1NJYjNEUUVCQ3dVQU1JR1FNUXN3Q1FZRFZRUUdFd0pWVXpFVE1CRUcKQTFVRUNBd0tRMkZzYVdadmNtNXBZVEVXTUJRR0ExVUVCd3dOVTJGdUlFWnlZVzVqYVhOamJ6RU5NQXNHQTFVRUNnd0VUMnQwWVRFVQpNQklHQTFVRUN3d0xVMU5QVUhKdmRtbGtaWEl4RVRBUEJnTlZCQU1NQ0hSaGVHbHNiR0UwTVJ3d0dnWUpLb1pJaHZjTkFRa0JGZzFwCmJtWnZRRzlyZEdFdVkyOXRNQjRYRFRJeE1EY3dNVEV4TkRBMU5Wb1hEVE14TURjd01URXhOREUxTlZvd2daQXhDekFKQmdOVkJBWVQKQWxWVE1STXdFUVlEVlFRSURBcERZV3hwWm05eWJtbGhNUll3RkFZRFZRUUhEQTFUWVc0Z1JuSmhibU5wYzJOdk1RMHdDd1lEVlFRSwpEQVJQYTNSaE1SUXdFZ1lEVlFRTERBdFRVMDlRY205MmFXUmxjakVSTUE4R0ExVUVBd3dJZEdGNGFXeHNZVFF4SERBYUJna3Foa2lHCjl3MEJDUUVXRFdsdVptOUFiMnQwWVM1amIyMHdnZ0VpTUEwR0NTcUdTSWIzRFFFQkFRVUFBNElCRHdBd2dnRUtBb0lCQVFDQU1MUFgKakU3dzdTYmdyTVZNKzhOKzc3YWdRaU1HVFpUU1dpOWl3RHR4MTdsWS9zNHZZQ1g0MVc3WHJYeWhmY0FhUFhBazZZSGw0ZUM4ay9mVgp6ZmhQSm9pLzhVbEhMNytPMFA3WWNRRkcvSUV0dEF5ZElPKzA3Rks0N0l5MkVXRHZ2dFJuM2lBbm5valU1dnpTbVNEUnl6c3FTbFQwCkZoME5RREY3cW5xbVNVcGlCY0dHdWVINnQzMXgwMWFhTGtMSDVjVzBNNWdZNW5adlVLWDl4RXAxb3BXY0gyUjlGdmd1bGFqYmpzOU8KUWQ4VVJHMXllU2Rsb0NnajdPcGJCUHE2NEJzNk1lbDE5d0ZnNUdDNWR5SWhIdzh3MmN5eWtJNFdPYmZ0RWNHZDFCUVVYY0k0TURkYgptbkl0YjBFb0UrYnBQcGpVVE80RCsyQzR5WnpSMkRidkFnTUJBQUV3RFFZSktvWklodmNOQVFFTEJRQURnZ0VCQUN2bVNCWVFNbTluCmc3TVpOVkV3VklqcjRvcG1UZEFyYmxHbkMvZVlveEphdUEyU3lFWTNqcndKQTVSTFNKNmF0MFR4d3VhU2JYVmkveXhEZUVEajdCRDcKbXZ2ai9GT1Z3WlY5L3FnN0xzY2N3RElhaWUzYlFtOEdiSWR6U1doa1hUYzJlVEJiRWY1d05yY3gwQWk0NGwrTG55VUxwb2JDajlnaQp4TE5JK0pEajVWTTczMndZOFBETXFpUFA4S1pSSXQxeTh0bEVLdW9IZ09iQmpwUGcxdXdCTVpoaUxKRzdySTBNT3V5M1lsN21vUVUwClBOSk00RW1aS0JuRzFHd2NDZFYzUGpXUnJuVzI2S2NpcnBaRHlRaVJ4ZkFTc25zaGxHLzJJYWdXWnNQckxwL3ZPR0hLMkJnaWNQRkwKU0hjYU9ROFNObmkzbERwK1RMSlVxMDh6Rkg0PTwvZHM6WDUwOUNlcnRpZmljYXRlPjwvZHM6WDUwOURhdGE+PC9kczpLZXlJbmZvPjwvZHM6U2lnbmF0dXJlPjxzYW1sMnA6U3RhdHVzIHhtbG5zOnNhbWwycD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIj48c2FtbDJwOlN0YXR1c0NvZGUgVmFsdWU9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpzdGF0dXM6U3VjY2VzcyIvPjwvc2FtbDJwOlN0YXR1cz48c2FtbDI6QXNzZXJ0aW9uIElEPSJpZDQxNDMzNjk2MjEzMzU3NTYxMjUzOTAyNzk0IiBJc3N1ZUluc3RhbnQ9IjIwMjEtMDctMTRUMDk6MTg6MjguMzczWiIgVmVyc2lvbj0iMi4wIiB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+PHNhbWwyOklzc3VlciBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQtZm9ybWF0OmVudGl0eSIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPmh0dHA6Ly93d3cub2t0YS5jb20vZXhrNzVidGZYVmhoMFRTcWU2OTU8L3NhbWwyOklzc3Vlcj48ZHM6U2lnbmF0dXJlIHhtbG5zOmRzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIj48ZHM6U2lnbmVkSW5mbz48ZHM6Q2Fub25pY2FsaXphdGlvbk1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjxkczpTaWduYXR1cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhMjU2Ii8+PGRzOlJlZmVyZW5jZSBVUkk9IiNpZDQxNDMzNjk2MjEzMzU3NTYxMjUzOTAyNzk0Ij48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8+PC9kczpUcmFuc2Zvcm1zPjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz48ZHM6RGlnZXN0VmFsdWU+YmYwbFAyWFJsYXIyRkc0V05EWTQ1NHB3S05vNnlTNkVyTEpoUW5aRkRHST08L2RzOkRpZ2VzdFZhbHVlPjwvZHM6UmVmZXJlbmNlPjwvZHM6U2lnbmVkSW5mbz48ZHM6U2lnbmF0dXJlVmFsdWU+SXh1MC9OUW9lemF3d01EZU1uSFA3OWRHOXo5cHpWOWc1THdYN01QdVNoSUh6ZEFlcDZVWFNPL3MyallEaThqNDdZUUsxMFhCTWxWWFZ6eGdVNXVPTm1TdFBISVYrOWVhTUVRVWRQNmZNMU1YQ1YxV0orNThCcWtuUGRxTUd3bHliL3NoQTkyNXJrVzR3WWlGcGpXUEkzN09WN0NIemRobENoZWN0bU9aSm9jSll1QXp6a3NkaFgvalp1WStNQnE0K2ZjcFNFZWJ1dlRzUEVhdjJHT2dUMTQ4VmFhVmhtVXFMdlJEY3FqZStvOE5kZUZUdzV1OExkTjIzUm9YaDFTamFGL0xvV2ZVSXA4VHdjN1RwT1hEaHhtQUhnSFQveGpoTHd2cFBhc1JzemhWTjUyS0hnT28wQjFFN2pkMlBUc1hYMzd2Zmp3dG5LTnRycDlqRzB2QmdBPT08L2RzOlNpZ25hdHVyZVZhbHVlPjxkczpLZXlJbmZvPjxkczpYNTA5RGF0YT48ZHM6WDUwOUNlcnRpZmljYXRlPk1JSURvRENDQW9pZ0F3SUJBZ0lHQVhwaDRCeWhNQTBHQ1NxR1NJYjNEUUVCQ3dVQU1JR1FNUXN3Q1FZRFZRUUdFd0pWVXpFVE1CRUcKQTFVRUNBd0tRMkZzYVdadmNtNXBZVEVXTUJRR0ExVUVCd3dOVTJGdUlFWnlZVzVqYVhOamJ6RU5NQXNHQTFVRUNnd0VUMnQwWVRFVQpNQklHQTFVRUN3d0xVMU5QVUhKdmRtbGtaWEl4RVRBUEJnTlZCQU1NQ0hSaGVHbHNiR0UwTVJ3d0dnWUpLb1pJaHZjTkFRa0JGZzFwCmJtWnZRRzlyZEdFdVkyOXRNQjRYRFRJeE1EY3dNVEV4TkRBMU5Wb1hEVE14TURjd01URXhOREUxTlZvd2daQXhDekFKQmdOVkJBWVQKQWxWVE1STXdFUVlEVlFRSURBcERZV3hwWm05eWJtbGhNUll3RkFZRFZRUUhEQTFUWVc0Z1JuSmhibU5wYzJOdk1RMHdDd1lEVlFRSwpEQVJQYTNSaE1SUXdFZ1lEVlFRTERBdFRVMDlRY205MmFXUmxjakVSTUE4R0ExVUVBd3dJZEdGNGFXeHNZVFF4SERBYUJna3Foa2lHCjl3MEJDUUVXRFdsdVptOUFiMnQwWVM1amIyMHdnZ0VpTUEwR0NTcUdTSWIzRFFFQkFRVUFBNElCRHdBd2dnRUtBb0lCQVFDQU1MUFgKakU3dzdTYmdyTVZNKzhOKzc3YWdRaU1HVFpUU1dpOWl3RHR4MTdsWS9zNHZZQ1g0MVc3WHJYeWhmY0FhUFhBazZZSGw0ZUM4ay9mVgp6ZmhQSm9pLzhVbEhMNytPMFA3WWNRRkcvSUV0dEF5ZElPKzA3Rks0N0l5MkVXRHZ2dFJuM2lBbm5valU1dnpTbVNEUnl6c3FTbFQwCkZoME5RREY3cW5xbVNVcGlCY0dHdWVINnQzMXgwMWFhTGtMSDVjVzBNNWdZNW5adlVLWDl4RXAxb3BXY0gyUjlGdmd1bGFqYmpzOU8KUWQ4VVJHMXllU2Rsb0NnajdPcGJCUHE2NEJzNk1lbDE5d0ZnNUdDNWR5SWhIdzh3MmN5eWtJNFdPYmZ0RWNHZDFCUVVYY0k0TURkYgptbkl0YjBFb0UrYnBQcGpVVE80RCsyQzR5WnpSMkRidkFnTUJBQUV3RFFZSktvWklodmNOQVFFTEJRQURnZ0VCQUN2bVNCWVFNbTluCmc3TVpOVkV3VklqcjRvcG1UZEFyYmxHbkMvZVlveEphdUEyU3lFWTNqcndKQTVSTFNKNmF0MFR4d3VhU2JYVmkveXhEZUVEajdCRDcKbXZ2ai9GT1Z3WlY5L3FnN0xzY2N3RElhaWUzYlFtOEdiSWR6U1doa1hUYzJlVEJiRWY1d05yY3gwQWk0NGwrTG55VUxwb2JDajlnaQp4TE5JK0pEajVWTTczMndZOFBETXFpUFA4S1pSSXQxeTh0bEVLdW9IZ09iQmpwUGcxdXdCTVpoaUxKRzdySTBNT3V5M1lsN21vUVUwClBOSk00RW1aS0JuRzFHd2NDZFYzUGpXUnJuVzI2S2NpcnBaRHlRaVJ4ZkFTc25zaGxHLzJJYWdXWnNQckxwL3ZPR0hLMkJnaWNQRkwKU0hjYU9ROFNObmkzbERwK1RMSlVxMDh6Rkg0PTwvZHM6WDUwOUNlcnRpZmljYXRlPjwvZHM6WDUwOURhdGE+PC9kczpLZXlJbmZvPjwvZHM6U2lnbmF0dXJlPjxzYW1sMjpTdWJqZWN0IHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6dW5zcGVjaWZpZWQiPnNrbmFkaW1wYWxsaUB0YXhpbGxhLmNvbTwvc2FtbDI6TmFtZUlEPjxzYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uIE1ldGhvZD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmNtOmJlYXJlciI+PHNhbWwyOlN1YmplY3RDb25maXJtYXRpb25EYXRhIEluUmVzcG9uc2VUbz0iYTI4MDhjOWZoN2ljajhqajQ2MGppZDJlZzU1aTBoZyIgTm90T25PckFmdGVyPSIyMDIxLTA3LTE0VDA5OjIzOjI4LjM3M1oiIFJlY2lwaWVudD0iaHR0cDovL2xvY2FsaG9zdDo5MDkwL3NhbWwvU1NPIi8+PC9zYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uPjwvc2FtbDI6U3ViamVjdD48c2FtbDI6Q29uZGl0aW9ucyBOb3RCZWZvcmU9IjIwMjEtMDctMTRUMDk6MTM6MjguMzczWiIgTm90T25PckFmdGVyPSIyMDIxLTA3LTE0VDA5OjIzOjI4LjM3M1oiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6QXVkaWVuY2VSZXN0cmljdGlvbj48c2FtbDI6QXVkaWVuY2U+aHR0cDovL2xvY2FsaG9zdDo5MDkwL3NhbWwvbWV0YWRhdGE8L3NhbWwyOkF1ZGllbmNlPjwvc2FtbDI6QXVkaWVuY2VSZXN0cmljdGlvbj48L3NhbWwyOkNvbmRpdGlvbnM+PHNhbWwyOkF1dGhuU3RhdGVtZW50IEF1dGhuSW5zdGFudD0iMjAyMS0wNy0xNFQwOToxODoyNy4zOTZaIiBTZXNzaW9uSW5kZXg9ImEyODA4YzlmaDdpY2o4amo0NjBqaWQyZWc1NWkwaGciIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6QXV0aG5Db250ZXh0PjxzYW1sMjpBdXRobkNvbnRleHRDbGFzc1JlZj51cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YWM6Y2xhc3NlczpQYXNzd29yZFByb3RlY3RlZFRyYW5zcG9ydDwvc2FtbDI6QXV0aG5Db250ZXh0Q2xhc3NSZWY+PC9zYW1sMjpBdXRobkNvbnRleHQ+PC9zYW1sMjpBdXRoblN0YXRlbWVudD48L3NhbWwyOkFzc2VydGlvbj48L3NhbWwycDpSZXNwb25zZT4=");
//        System.out.println("decode " + new String(decode));
    }
}