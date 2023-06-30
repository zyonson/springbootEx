# フレンド申請機能つき掲示板
## 目的
springbootの機能を一通り学習するために作ったアプリケーションになります。

作成した機能
- ログイン機能
- 投稿機能
- 投稿に基づいたコメント機能
- フレンド申請機能

## インストール方法
1. git cloneする
2. Eclipseにプロジェクトをインポートする。
3. DBを作成する。
4. アプリのDB接続設定を確認する。
5. Eclipseでプロジェクトを起動する。

# 設計
使用した技術、及び実行環境は以下になります。

##　実行環境

- OS : macOS version 13.3.1(a)
- IDE : Eclipse 2022-12
- Java :
- SpringBoot
- MySQL : 8.0.33

## DB設計

![eg_sns_erd](https://github.com/zyonson/springbootEx/assets/96770605/0bffee3f-80c3-4a8b-8dc5-c89b67554ca2)

## パッケージ構成図
```bash
src
└─main
    ├─java
    │  └─jp
    │      └─dcworks
    │          └─engineersgate
    │              └─egbbs
    │                  ├─controller      # コントローラクラスの管理。
    │                  ├─core            # コアクラスの管理。アプリ基底処理及び、設定に関する処理のプログラム群。
    │                  │  └─annotation
    │                  ├─dto             # DTOクラスの管理。入力フォーム関連。
    │                  ├─entity          # DBエンティティクラスの管理。
    │                  ├─repository      # DBアクセスリポジトリ。
    │                  ├─service         # リポジトリをラップしたサービスクラス等。他、必要に応じて外部連携等コンポーネント管理。
    │                  └─util            # ユーティリティクラスの管理。
    └─resources
        ├─static                          # 静的ファイル。js、css等。
        │  └─assets
        └─templates                       # テンプレートファイル。
            ├─account
            ├─common
            ├─error
            ├─home
            ├─login
            └─topic
```

# 画面説明

## ログイン画面 
ログイン画面です。
<img width="1250" alt="login" src="https://github.com/zyonson/springbootEx/assets/96770605/2bdf0ac9-5203-4143-b89b-32701c30bd03">

- ログインID、パスワードを入力しログインします。
- ログインID、パスワードは必須入力です。

<img width="1249" alt="loginValidationJs" src="https://github.com/zyonson/springbootEx/assets/96770605/c9b203ca-9aab-411a-a020-5edd3f0c7b9f">

- ログインID、パスワードを入力しなかった場合、jsでバリデーションを行いエラーとします。

<img width="1246" alt="loginValidation" src="https://github.com/zyonson/springbootEx/assets/96770605/7dd652d1-5789-461b-a174-002478ea4353">

- HTMLの改ざんを考慮し、jsのバリデーションを改ざんされた場合、サーバサイドでバリデーションを行います。

## アカウント作成画面
アカウント作成画面です。
<img width="1250" alt="createAccount" src="https://github.com/zyonson/springbootEx/assets/96770605/744fce8d-f1c8-4856-90ad-314cffd6226d">

- 「お名前」「ログインID」「パスワード」を入力し、ログインします。
- 「お名前」「ログインID」「パスワード」は必須入力です。

<img width="1350" alt="createAccountValidationJs" src="https://github.com/zyonson/springbootEx/assets/96770605/fdb01221-66c8-48f4-b50f-fdd65d8c75b0">

- 「お名前」「ログインID」「パスワード」を入力しなかった場合、jsでバリデーションを行いエラーとします。

<img width="1234" alt="createAccountValidation" src="https://github.com/zyonson/springbootEx/assets/96770605/b367410e-90b3-4372-9b25-3f92443fa760">

- HTMLの改ざんを考慮し、jsのバリデーションを改ざんされた場合、サーバサイドでバリデーションを行います。

<img width="1248" alt="accountComplete" src="https://github.com/zyonson/springbootEx/assets/96770605/79eb49d8-c19e-4f6f-b842-b568e9b3ec06">

- 正常に入力が行われた場合、完了画面へ遷移しアカウントが登録されます。

## ホーム画面
<img width="1230" alt="home" src="https://github.com/zyonson/springbootEx/assets/96770605/427f4701-9e3b-465f-833c-e86ba47923cf">

## 投稿画面
投稿画面です。

<img width="1230" alt="home" src="https://github.com/zyonson/springbootEx/assets/96770605/427f4701-9e3b-465f-833c-e86ba47923cf">

- 「タイトル」「本文」を入力し「写真」を選択し、投稿します。

<img width="1231" alt="postValidationJs" src="https://github.com/zyonson/springbootEx/assets/96770605/e251a882-da91-4ec4-b107-cd2c6eb3fb1b">

- 「タイトル」「本文」を入力しなかった場合、また「写真」を選択しなかった場合、jsでバリデーションを行いエラーとします。

<img width="1237" alt="successPost" src="https://github.com/zyonson/springbootEx/assets/96770605/081e8281-a727-4ca2-a0bc-519c5e053fa5">

- 投稿に成功した際、ホーム画面に遷移し、フラッシュメッセージを表示します。

## コメント画面
投稿にコメントをする機能です。

<img width="936" alt="comment" src="https://github.com/zyonson/springbootEx/assets/96770605/7aa6bdc3-af14-496c-824b-886c0d3cb7f2">

- 「コメント」を入力します。

<img width="936" alt="commentValidationJs" src="https://github.com/zyonson/springbootEx/assets/96770605/ff06323a-b7ed-451b-9cb7-6229c871d966">

- 「コメント」を入力しなかった場合、jsでバリデーションを行いエラーとします。

<img width="1233" alt="commentValidation" src="https://github.com/zyonson/springbootEx/assets/96770605/83e3e0e8-b6bc-499c-9748-c451459bd9aa">

- HTMLの改ざんを考慮し、jsのバリデーションを改ざんされた場合、サーバサイドでバリデーションを行います。

## プロフィール画面
プロフィール画面になります。ログインしているユーザー自身のプロフィール画面の場合、プロフィールやパスワードを変更できます。

<img width="1233" alt="editProfile" src="https://github.com/zyonson/springbootEx/assets/96770605/01c2de56-b665-40b1-ba39-3ce075f3a802">

- プロフィール編集画面になります。

<img width="1232" alt="editProfileJs" src="https://github.com/zyonson/springbootEx/assets/96770605/00bf4aec-e7a0-491f-8a30-3e640cc03bd7">

- 「お名前」「メールアドレス」を入力しなかった場合、jsでバリデーションを行いエラーとします。

<img width="1235" alt="editPassword" src="https://github.com/zyonson/springbootEx/assets/96770605/2c97c353-c311-49bc-93ff-d52a91093033">

- パスワード編集画面になります。
  
<img width="1234" alt="editPasswordValidationJs" src="https://github.com/zyonson/springbootEx/assets/96770605/1b35d3f7-f2c2-45a6-b53b-6505f3cc3b41">

「現在のパスワード」「新しいパスワード」を入力しなかった場合、jsでバリデーションを行いエラーとします。
