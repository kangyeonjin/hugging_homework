from fastapi import FastAPI
from router.router import router

app = FastAPI()

#라우터 등록
app.include_router(router)

#서버 실행 명령
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)
