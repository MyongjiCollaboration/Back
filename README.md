# 멋쟁이 사자처럼 12기 명지톤 서버 2조

# MJU LIKELION

## 📕 12th Lecture

### Web
[Notion](https://likelion-mju-12th.notion.site/WEB-10a88d96c88f491096654e6d7d683b50?pvs=4)

[Repo](https://github.com/mju-likelion/12th-web-session)

### Server
[Notion](https://likelion-mju-12th.notion.site/SERVER-86f934ae17d348a3923ec7bf14186b38?pvs=4)

[Repo](https://github.com/mju-likelion/12th-server-session)

⁕ notion의 경우 권한에 따라 보이지 않을 수 있습니다. 해당 기수의 운영진에게 문의주세요

## 📠 Convention

### 🤝 Branch Naming Convention

|  머릿말      | 설명        |
| ----------- | ---------- |
| master      | 서비스 브랜치    |
| develop     | 배포 전 작업 기준    |
| feature     | 기능 단위 구현    |
| hotfix      | 서비스 중 긴급 수정 건에 대한 처리   |

<details>
<summary>Branch Naming Convention Detail</summary>
<div markdown="1">

```
master(main) ── develop ── feature
└── hotfix
```
- [ ] [깃 플로우](https://techblog.woowahan.com/2553/)를 베이스로 하여 프로젝트 사이즈에 맞게 재정의했습니다.
- [ ] 브랜치 이름은 `cabab-case`를 따릅니다.

#### master(main)
- [ ] 실제 서비스가 이루어지는 브랜치입니다.
- [ ] 이 브랜치를 기준으로 develop 브랜치가 분기됩니다.
- [ ] 배포 중, 긴급하게 수정할 건이 생길시 hotfix 브랜치를 만들어 수정합니다.

#### develop
- [ ] 개발, 테스트, 릴리즈 등 배포 전 작업의 기준이 되는 브랜치입니다.
- [ ] 해당 브랜치를 default로 설정합니다.
- [ ] 이 브랜치에서 feature 브랜치가 분기됩니다.

#### feature
- [ ] 개별 개발자가 맡은 작업을 개발하는 브랜치입니다.
- [ ] feature/(feature-name) 과 같이 머릿말을 feature, 꼬릿말을 개발하는 기능으로 명명합니다.
- [ ] feature-name의 경우 cabab-case를 따릅니다.
- [ ] ex) feature/login-validation

#### hotfix
- [ ] 서비스 중 긴급히 수정해야 할 사항이 발생할 때 사용합니다.
- [ ] master에서 분기됩니다.

</div>
</details>

### 🤝 Commit Convention

|  머릿말     | 설명        |
| ----------- | ---------- |
| feat        | 기능 구현, 추가   |
| setting     | 패키지 설치, 개발 설정    |
| refactor    | 코드 리팩터링    |
| fix         | 버그 수정, 예외 케이스 대응, 기능 개선   |
| docs        | README.md 작성, 주석 작성   |
| chore       | 기타 작업  |

<details>
<summary>Commit Convention Detail</summary>
<div markdown="1">

- [ ] `feat: 회원가입 API 구현`과 같이 `머릿말: 내용` 형식으로 작성합니다.
- [ ] 리팩터링의 경우 기능의 변화 없이 구조를 개선할 때 사용됩니다. (ex: 입력 상태값을  커스텀 훅으로 분리)
- [ ] 여러 작업을 동시에 실행한 경우 한 줄에 한 내용씩 입력합니다. 가장 메인이 된 작업을 먼저 기입합니다.
```
- ❌ 잘못된 예시_1
feat: 버튼 컴포넌트 구현, API 중복 요청 현상 해결

- ❌ 잘못된 예시_2
feat: 버튼 컴포넌트 구현 || fix: API 중복 요청 현상 해결

- ⭕ 올바른 예시
feat: 버튼 컴포넌트 구현
fix: API 중복 요청 현상 해결
```

</div>
</details>


![1](https://github.com/user-attachments/assets/a889cd2e-5165-43bd-a028-20965594f650)
![2](https://github.com/user-attachments/assets/c28a71ad-0477-4c45-9639-761900e7a978)
![3](https://github.com/user-attachments/assets/5f840b62-65e8-4006-a5b3-d81073626a37)
![5](https://github.com/user-attachments/assets/78248c9d-05fb-429c-b3e8-4ebe55f55365)
![8](https://github.com/user-attachments/assets/a9b42459-780a-4d41-a7b5-fadf58553b55)
![9](https://github.com/user-attachments/assets/446b1c99-1ad6-4921-9c1b-2533ed3d847b)
![10](https://github.com/user-attachments/assets/d98c7464-18be-47ad-a282-242bdfe8af66)

