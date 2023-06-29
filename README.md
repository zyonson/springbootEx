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

ログイン画面 

